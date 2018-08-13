/* globals module */

module.exports = {
  ngProvider: 'controller',
  ngModule: 'controllers',
  ngName: 'dashbot.visuals.number-controller',
  dependencies: ['$scope', '$timeout', '$http'],
  fn: function($scope, $timeout, $http) {
    'use strict';

    var
      _format = function() {
        if ($scope.visual.unit) {
          if ($scope.visual.unit === 'bytes') {
            if ($scope.value < 1024) {
              $scope.unit = 'b';
            } else if ($scope.value < 1024 * 1024) {
              $scope.unit = 'kb';
              $scope.value = Math.round($scope.value / 1024);
            } else {
              $scope.unit = 'mb';
              $scope.value = Math.round($scope.value / (1024 * 1024));
            }
          } else if ($scope.visual.unit === 'percent') {
            $scope.unit = 'percent';
            $scope.value = Math.round($scope.value);
          }
        }
      },

      _getJson = function() {
        var
          apiURL = $scope.visual.xhr,
          newValue;

        $scope.visual.loading = true;
        $http.get(apiURL)
          .success(function(data) {
            newValue = eval('data.' + $scope.visual.xhrValue);

            if (newValue === '?') {
              $scope.value = '?';
              $scope.visual.loading = false;
              $scope.visual.build = 'red';
            }

            else if (!isNaN(newValue)) {
              $scope.value = newValue;
              _format();
              if ($scope.visual.green && $scope.visual.red) {
                $scope.visual.build = eval(
                  $scope.value + $scope.visual.green + '? "green" : (' +
                $scope.value + $scope.visual.red + ' ? "red" : "none") '
                );
              }

              $timeout(function() {
                $scope.visual.loading = false;
              }, 1000);
            }

            if ($scope.visual.xhrInterval) {
              $timeout(_getJson, $scope.visual.xhrInterval * 1000);
            }
          });
      };

    if ($scope.visual.visual === 'number') {
      if ($scope.visual.xhr) {
        _getJson();
      } else {
        $scope.value = $scope.visual.value;
        _format();
      }
    }
  }
};
