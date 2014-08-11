Frontend.AuthorsRoute = Ember.Route.extend({
  model: function() {
    return this.get('store').find('author');
  }
});

