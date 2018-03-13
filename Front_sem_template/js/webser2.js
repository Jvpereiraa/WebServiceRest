(function () {
    'use strict';

    angular.module('listar',  [])

        .controller('WebsController', function ($scope, $http) {

            // lista de usuários pré cadastrados
            $http({
                method: 'GET',
                url: 'http://jsonplaceholder.typicode.com/comments'               
                }).then(function successCallback(response) {
                    $scope.comentarios = response.data;
                }, function errorCallback(response) {

            });

        });

})();

