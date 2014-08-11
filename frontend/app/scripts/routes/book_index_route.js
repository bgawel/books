Frontend.BookIndexRoute = Ember.Route.extend({
  model: function() {
    return this.modelFor('book');
  }
});