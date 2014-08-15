Frontend.BooksCreateRoute = Ember.Route.extend({
  renderTemplate: function() {
    this.render('book.create');
  },
  setupController: function(controller){
    controller.set('model', Ember.Object.create({authors:[Ember.Object.create({id: -new Date().getTime()})]}));
  }
});