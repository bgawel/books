Frontend.AuthorEditRoute = Ember.Route.extend({
  model: function(params) {
    return this.get('store').find('author', this.modelFor('author').id);
  },
  setupController: function(controller, model){
    controller.set('model', model);
  },
  renderTemplate: function() {
    this._super();
    this.render('author.edit', { into: 'authors' });
  }
});

