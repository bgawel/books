Frontend.BookRoute = Ember.Route.extend({
  model: function(params) {
    return this.get('store').find('book', params.book_id);
  }
});

