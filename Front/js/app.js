(function () {
    'use strict';

    angular.module('cadastro',  [])

        .controller('UsuarioController', function ($scope, $http) {

            // lista de usuários pré cadastrados
            $http({
                method: 'GET',
                url: 'http://localhost:8080/Fornecedor/webresources/generic/Usuario/list'               
                }).then(function successCallback(response) {
                    $scope.usuarios = response.data;
                }, function errorCallback(response) {

            });

            //inicialização de um usuário vazio
            $scope.usuario =  {id:'', nome:'', email:'', comment:'', cnpj:''};
            $scope.oldUsuario=  {id:'', nome:'', email:'', comment:'', cnpj:''};

            $scope.findUserById = function(){
                $http({
                    method: 'GET',
                    url: 'http://localhost:8080/Fornecedor/webresources/generic/Usuario/get/' + $scope.usuario.id              
                    }).then(function successCallback(response) {
                        if(response.data != null){
                            $scope.usuarios = [response.data];
                        }else{
                            window.alert("Falha ao buscar");
                        }
                        
                    }, function errorCallback(response) {

                });
                $scope.usuario =  {id:'', nome:'', email:'', comment:'', cnpj:''};    
            }

            $scope.postUser = function(){               
                $http.post('http://localhost:8080/Fornecedor/webresources/generic/Usuario/inserir', $scope.usuario)
                    .then(function successCallback(response) {
                        if(response.data != "false"){
                            window.alert("Inserido com sucesso");
                        }else{
                            window.alert("Falha ao inserir");
                        }
                    }, function errorCallback(response) {

                });
                $scope.usuario =  {id:'', nome:'', email:'', comment:'', cnpj:''};    
            }


            $scope.deleteUserById = function($id){
                $http({
                    method: 'DELETE',
                    url: 'http://localhost:8080/Fornecedor/webresources/generic/Usuario/delete/' + $id              
                    }).then(function successCallback(response) {
                        console.log("boa");
                        if(response.data != "false"){
                            window.alert("Deletado com sucesso");
                        }else{
                            window.alert("Falha ao deletar");
                        }
                        window.location.reload();
                    }, function errorCallback(response) {

                });        
                       
                console.log($id);
                $scope.usuario =  {id:'', nome:'', email:'', comment:'', cnpj:''};    
            }

            $scope.saveData = function($id){
                 $http({
                    method: 'GET',
                    url: 'http://localhost:8080/Fornecedor/webresources/generic/Usuario/get/' + $id              
                    }).then(function successCallback(response) {
                        $scope.oldUsuario = response.data;
                        // window.location.replace("atualizar.html");    
                    }, function errorCallback(response) {

                });
            }

            $scope.updateUserById = function($id){
               // $location.path('file:///home/zelphy/Front/blog-files/criando-uma-aplicacao-angularjs/atualizar.html');
                $http.put('http://localhost:8080/Fornecedor/webresources/generic/Usuario/alterar', $scope.usuario             
                    ).then(function successCallback(response) {
                        console.log("boa");
                        console.log(response.data);
                        if(response.data != "false"){
                            window.alert("Atualizado com sucesso");
                        }else{
                            window.alert("Falha ao Deletar");
                        }
                    }, function errorCallback(response) {
                         window.alert("Falha ao atualizar");
                });        
                       
                console.log($id);
                $scope.usuario =  {id:'', nome:'', email:'', comment:'', cnpj:''};    
            }
        });

})();

