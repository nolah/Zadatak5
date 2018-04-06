'use strict';

var gulp = require('gulp');
var browserSync = require('browser-sync');

var $ = require('gulp-load-plugins')();
var gutil = require('gulp-util');

var wiredep = require('wiredep').stream;

module.exports = function (options) {
    gulp.task('styles', function () {
        var sassOptions = {
            options: [
                'bower_components',
                options.src + '/app/styles'
            ]
        };

        var injectFiles = gulp.src([
            options.src + '/app/styles/**/*',
            '!' + options.src + '/app/styles/index.scss'
        ], {
            read: false
        });

        var injectOptions = {
            transform: function (filePath) {
                filePath = filePath.replace(options.src + '/adminportal/app/', '');
                return '@import \'' + filePath + '\';';
            },
            starttag: '// injector',
            endtag: '// endinjector',
            addRootSlash: true
        };

        var indexFilter = $.filter('index.scss');

        return gulp.src([
                options.src + '/app/styles/index.scss'
            ])
            .pipe(indexFilter)
            .pipe($.inject(injectFiles, injectOptions))
            .pipe(indexFilter.restore())
            .pipe($.sourcemaps.init())
            .pipe($.sass(sassOptions)).on('error', options.errorHandler('Sass'))
            .pipe($.autoprefixer()).on('error', options.errorHandler('Autoprefixer'))
            .pipe($.sourcemaps.write())
            .pipe(gulp.dest(options.tmp + '/serve/portal/app/styles/'))
            .pipe(browserSync.reload({
                stream: true 
            }));
    });
};