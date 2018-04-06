'use strict';

var gulp = require('gulp');
var $ = require('gulp-load-plugins')();
var wiredep = require('wiredep').stream;
var fileinclude = require('gulp-file-include');
var babel = require('gulp-babel');

module.exports = function (options) {

    gulp.task('inject', ['scripts', 'styles', 'images'], function () {
        var injectStyles = gulp.src([
            options.tmp + '/serve/portal/app/styles/**/*.css'
        ], {
            read: false
        });
        var injectHtmls = gulp.src([options.src + '/**/*.html'])
            .pipe(fileinclude())
            .pipe(gulp.dest(options.tmp + '/serve/src'));
        var injectScripts = gulp.src([
                options.src + '/app/**/*.js',
                '!' + options.src + '/app/**/*.spec.js',
                '!' + options.src + '/app/**/*.mock.js'
            ])
            .pipe(babel({
                presets: ['es2015']
            }))
            .pipe($.angularFilesort()).on('error', options.errorHandler('AngularFilesort'));
        var injectOptions = {
            ignorePath: [options.src, options.tmp + '/serve'],
            addRootSlash: true
        };
        return gulp.src(options.src + '/**/*.html')
            .pipe($.inject(injectHtmls, injectOptions))
            .pipe($.inject(injectStyles, injectOptions))
            .pipe($.inject(injectScripts, injectOptions))
            .pipe(wiredep(options.wiredep))
            .pipe(gulp.dest(options.tmp + '/serve'));
    });
};