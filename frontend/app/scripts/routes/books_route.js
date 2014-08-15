Frontend.BooksRoute = Ember.Route.extend({
  model: function() {
    return this.get('store').find('book');
  }
});

/*Frontend.ModelViewRoute = Ember.Route.extend({
  actions: {
    reload: function() {
      this.get('model').reload();
    }
  }
}); does not work*/

