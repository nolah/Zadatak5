'use strict';

var gulp = require('gulp');
var browserSync = require("browser-sync");

function isOnlyChange(event) {
    return event.type === 'changed';
}

module.exports = function (options) {
    gulp.task('watch', ['inject'], function () {

        gulp.watch([options.src + '/*.html', 'bower.json'], ['inject']);

        gulp.watch([
            options.src + '/app/styles/**/*.css',
            options.src + '/app/styles/**/*.scss'
        ], function (event) {
            if (isOnlyChange(event)) {
                gulp.start('styles');
            } else {
                gulp.start('inject');
            }
        });

        gulp.watch(options.src + '/app/**/*.js', function (event) {
            if (isOnlyChange(event)) {
                gulp.start('scripts');
            } else {
                gulp.start('inject');
            }
        });

        gulp.watch(options.src + '/app/**/*.html', function (event) {
            if (isOnlyChange(event)) {
                gulp.start('html-serve');
                browserSync.reload(event.path);
            } else {
                gulp.start('inject');
            }
        });

        gulp.watch(options.src + '/app/img/**/*', function (event) {
            if (isOnlyChange(event)) {
                gulp.start('images');
                browserSync.reload(event.path);
            } else {
                gulp.start('inject');
            }
        });
    });
};