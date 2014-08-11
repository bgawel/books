Frontend.AuthorRoute = Ember.Route.extend({
  model: function(params) {
    return this.get('store').find('author', params.author_id);
  }
});

