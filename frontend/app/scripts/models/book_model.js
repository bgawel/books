/*global Ember*/
Frontend.Book = DS.Model.extend({
    title: DS.attr('string'),
    isbn: DS.attr('string'),
    authors: DS.hasMany('author', {async: true})
});

Frontend.BookSerializer = DS.RESTSerializer.extend({
  serializeIntoHash: function(hash, type, record, options) {
    Ember.merge(hash, this.serialize(record, options));
  },
  serializeHasMany: function(record, json, relationship) {
      var key = relationship.key,
          property = Ember.get(record, key),
          relationshipType = DS.RelationshipChange.determineRelationshipType(record.constructor, relationship);

      if (property && relationshipType === 'manyToNone' || relationshipType === 'manyToMany' ||
          relationshipType === 'manyToOne') {

          // Add each serialized nested object
          json[key] = [];
          property.forEach(function(item, index){
              json[key].push(item.serialize());
          });
      }
  }
});

// delete below here if you do not want fixtures
Frontend.Book.FIXTURES = [
  
  {
    id: 0,
    isbn: '123',
    title: 'foo'
    
  },
  
  {
    id: 1,
    isbn: '123',
    title: 'foo'
    
  }
  
];
