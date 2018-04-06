'use strict';

var gulp = require('gulp');
var browserSync = require('browser-sync');
var imageMin = require('gulp-imagemin');

module.exports = function (options) {

    gulp.task('images', function () {
        return gulp.src([
                options.src + '/app/img/**/*'
            ])
            .pipe(imageMin())
            .pipe(gulp.dest(options.tmp + '/serve/portal/app/img/'))
            .pipe(browserSync.reload({
                stream: true
            }));
    });
};