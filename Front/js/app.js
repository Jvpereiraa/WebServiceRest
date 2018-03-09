(function () {
    'use strict';

    angular.module('cadastro',  [])

        .controller('UsuarioController', function ($scope, $http) {

            // lista de usuários pré cadastrados
            $http({
                method: 'GET',
                url: 'http://localhost:8080/Fornecedor/webresources/generic/Usuario/list',
                headers: {}
                }).then(function successCallback(response) {
                    $scope.usuarios = response.data;
                }, function errorCallback(response) {

            });

            //inicialização de um usuário vazio
            $scope.usuario =  {id:'', nome:'', email:'', comment:'', cnpj:''};

            //método para adicionar o usuário a lista
            // $scope.cadastrar = function () {
            //     $scope.usuarios.push($scope.usuario);
            //     $scope.usuario = {id:'', nome:'', email:'', comment:'', cnpj:''};
            //     $scope.statusSenha = {};
            // };

            //método para validar a senha do usuário
            // $scope.validarSenha = function () {
            //     $scope.statusSenha = {};

                // if ($scope.usuario.senha && $scope.usuario.senha.length >= 6) {
                //     $scope.statusSenha.classe = 'has-success';
                //     $scope.statusSenha.icone = 'glyphicon-ok';
                //     $scope.statusSenha.mensagem = 'Senha forte';

                // }else {
                //     $scope.statusSenha.classe = 'has-error';
                //     $scope.statusSenha.icone = 'glyphicon-remove';
                //     $scope.statusSenha.mensagem = 'Senha fraca';
                // }
            // };
        });

})();

