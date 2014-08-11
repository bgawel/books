// Visit links below to get convinced why *IndexRoute is needed
// http://discuss.emberjs.com/t/nested-resource-rendering-into-main-outlet-and-linkto-issue/1791/5
//http://stackoverflow.com/questions/16962258/redirecting-from-edit-to-parent-resource-doesnt-rerender-template
Frontend.AuthorIndexRoute = Ember.Route.extend({
  model: function() {
    return this.modelFor('author');
  }
});