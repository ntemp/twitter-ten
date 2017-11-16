/*global Backbone, jQuery, _ */
var app = app || {};

(function ($) {
    'use strict';

    // Tweet Item View
    // --------------

    // The DOM element for a tweet item...
    app.TweetView = Backbone.View.extend({
        //... is a list tag.
        tagName:  'li',

        // Cache the template function for a single item.
        template: _.template($('#tweet-template').html()),

        // Re-render the titles of the tweet item.
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
    });
})(jQuery);
