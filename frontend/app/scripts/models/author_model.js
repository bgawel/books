/*global Ember*/
Frontend.Author = DS.Model.extend({
    firstName: DS.attr('string'),
    lastName: DS.attr('string'),
    books: DS.hasMany('book', {async: true}),
    nbooks: DS.attr('number', {defaultValue: 0})
});

// to remove JSON root for POST and PUT
Frontend.AuthorSerializer = DS.RESTSerializer.extend({
  serializeIntoHash: function(hash, type, record, options) {
    Ember.merge(hash, this.serialize(record, options));
  }
});

// delete below here if you do not want fixtures
Frontend.Author.FIXTURES = [
  
  {
    id: 0,
    firstName: 'foo',
    lastName: 'foo',
    nbooks: 1
  },
  {
    id: 1,
    firstName: 'foo',
    lastName: 'foo',
    nbooks: 2
  }
  
];
