Frontend.AuthorsCreateController = Ember.ObjectController.extend({
  actions: {
    save: function() {
      var author = this.get('store').createRecord('author', {
        lastName: $('#lastName').val(),
        firstName: $('#firstName').val(),
      });
      var controller = this;
      author.save().then(function(response) {
        controller.transitionToRoute('author.edit', response);
      },function(response) {
        author.deleteRecord();
        console.error(response);
      });
    }
  }
});