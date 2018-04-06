'use strict';

var gulp = require('gulp');

var gulpNgConfig = require('gulp-ng-config');

module.exports = function (options) {
    gulp.task('config-development', function () {

        gulp.src('./environmentConfig.json')
            .pipe(gulpNgConfig('app.config', {
                environment: 'development'
            }))
            .pipe(gulp.dest('./src/app/'));
    });

    gulp.task('config-development-mock', function () {

        gulp.src('./environmentConfig.json')
            .pipe(gulpNgConfig('app.config', {
                environment: 'development-mock'
            }))
            .pipe(gulp.dest('./src/app/'));
    });

    gulp.task('config-development-remote', function () {

        gulp.src('./environmentConfig.json')
            .pipe(gulpNgConfig('app.config', {
                environment: 'development-remote'
            }))
            .pipe(gulp.dest('./src/app/'));
    });

    gulp.task('config-development-docker', function () {

        gulp.src('./environmentConfig.json')
            .pipe(gulpNgConfig('app.config', {
                environment: 'development-docker'
            }))
            .pipe(gulp.dest('./src/app/'));
    });

    gulp.task('config-deployment-develop', function () {

        gulp.src('./environmentConfig.json')
            .pipe(gulpNgConfig('app.config', {
                environment: 'deployment-develop'
            }))
            .pipe(gulp.dest('./src/app/'));
    });

    gulp.task('config-deployment-release', function () {

        gulp.src('./environmentConfig.json')
            .pipe(gulpNgConfig('app.config', {
                environment: 'deployment-release'
            }))
            .pipe(gulp.dest('./src/app/'));
    });

    gulp.task('config-deployment-master', function () {

        gulp.src('./environmentConfig.json')
            .pipe(gulpNgConfig('app.config', {
                environment: 'deployment-master'
            }))
            .pipe(gulp.dest('./src/app/'));
    });

    gulp.task('config-deployment-demo', function () {

        gulp.src('./environmentConfig.json')
            .pipe(gulpNgConfig('app.config', {
                environment: 'deployment-demo'
            }))
            .pipe(gulp.dest('./src/app/'));
    });

}