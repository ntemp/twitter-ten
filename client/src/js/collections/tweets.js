/*global Backbone */
var app = app || {};

(function () {
    'use strict';

    // Tweet Collection
    // ---------------
    var Tweets = Backbone.Collection.extend({
        url: '/twitterten-api/tweets',
    });

    // Create our global collection of **Tweets**.
    app.tweets = new Tweets();
})();
