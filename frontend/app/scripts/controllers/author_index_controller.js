Frontend.AuthorIndexController = Ember.ObjectController.extend({
  allBooksDisplayed: false,
  
  actions: {
    displayBooks: function() {
      this.set('allBooksDisplayed', true);
    },
    hideBooks: function() {
      this.set('allBooksDisplayed', false);
    }
  }

});