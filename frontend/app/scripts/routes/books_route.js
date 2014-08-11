Frontend.BooksRoute = Ember.Route.extend({
  model: function() {
    return this.get('store').find('book');
  }
});

