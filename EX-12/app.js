// Define AngularJS Module with ngRoute Dependency
var app = angular.module('myApp', ['ngRoute']);

// Configure Routes
app.config(function($routeProvider) {
  $routeProvider
    .when('/', {
      templateUrl: 'login.html',
      controller: 'loginController'
    })
    .when('/register', {
      templateUrl: 'register.html',
      controller: 'registerController'
    })
    .otherwise({
      redirectTo: '/'
    });
});

// Login Controller
app.controller('loginController', function($scope) {
  $scope.login = function() {
    if ($scope.userid && $scope.password) {
      alert('Login Successful!');
    } else {
      alert('Please enter User ID and Password!');
    }
  };
});

// Register Controller
app.controller('registerController', function($scope) {
  $scope.students = [];
  $scope.addStudent = function() {
    if ($scope.rollno && $scope.email && $scope.name && $scope.password) {
      $scope.students.push({
        rollno: $scope.rollno,
        email: $scope.email,
        name: $scope.name,
        password: $scope.password
      });
      alert('Student Registered Successfully!');
      $scope.rollno = '';
      $scope.email = '';
      $scope.name = '';
      $scope.password = '';
    } else {
      alert('Please fill all fields!');
    }
  };
});
