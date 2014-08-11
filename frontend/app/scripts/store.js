//Frontend.ApplicationAdapter = DS.FixtureAdapter;

Frontend.ApplicationAdapter = DS.RESTAdapter.extend({
  host: 'http://localhost:8080',
  pathForType: function(type) {
    return type;
  },
});