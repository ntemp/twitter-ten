/*global $ */
/*jshint unused:false */
var app = app || {};
var REFRESH_PERIOD = 60000;

$(function () {
	'use strict';

	// kick things off by creating the `App`
	new app.AppView();

    // Trigger a refresh every REFRESH_PERIOD
	window.setInterval(function() { app.tweets.fetch(); }, REFRESH_PERIOD)
});
