<!DOCTYPE html>
<html lang="en">
  <head>

    <title>Test Web Application</title>

  </head>
  <body ng-app="myApp" class="ng-cloak">
  <div class="generic-container" ng-controller="UserController as ctrl">
    <div class="panel panel-default">
      <div class="panel-heading"><span class="lead">Organiser</span></div>
      <div class="formcontainer">
        <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
          <input type="hidden" ng-model="ctrl.user.id" />
          <div class="row">
            <div class="form-group col-md-12">
              <label class="col-md-2 control-lable" for="file">Name</label>
              <div class="col-md-7">
                <input type="text" ng-model="ctrl.user.username" name="uname" class="username form-control input-sm" placeholder="Enter your name" required ng-minlength="3"/>
                <div class="has-error" ng-show="myForm.$dirty">
                  <span ng-show="myForm.uname.$error.required">required field</span>
                  <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                  <span ng-show="myForm.uname.$invalid">field is invalid </span>
                </div>
              </div>
            </div>
          </div>


          <div class="row">
            <div class="form-group col-md-12">
              <label class="col-md-2 control-lable" for="file">Location</label>
              <div class="col-md-7">
                <input type="text" ng-model="ctrl.user.location" class="form-control input-sm" placeholder="Enter your Address. [This field is validation free]"/>
              </div>
            </div>
          </div>

          <div class="row">
            <div class="form-group col-md-12">
              <label class="col-md-2 control-lable" for="file">Email</label>
              <div class="col-md-7">
                <input type="email" ng-model="ctrl.user.email" name="email" class="email form-control input-sm" placeholder="Enter your Email" required/>
                <div class="has-error" ng-show="myForm.$dirty">
                  <span ng-show="myForm.email.$error.required">required field</span>
                  <span ng-show="myForm.email.$invalid">field is invalid </span>
                </div>
              </div>
            </div>
          </div>

          <div class="row">
            <div class="form-actions floatRight">
              <input type="submit"  value="{{!ctrl.user.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset All</button>
            </div>
          </div>
        </form>
      </div>
    </div>
    <div class="panel panel-default">
      <!-- Default panel contents -->
      <div class="panel-heading"><span class="lead">list of candidates</span></div>
      <div class="tablecontainer">
        <table class="table table-hover">
          <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Location</th>
            <th>Email</th>
            <th width="20%"></th>
          </tr>
          </thead>
          <tbody>
          <tr ng-repeat="u in ctrl.users">
            <td><span ng-bind="u.id"></span></td>
            <td><span ng-bind="u.username"></span></td>
            <td><span ng-bind="u.location"></span></td>
            <td><span ng-bind="u.email"></span></td>
            <td>
              <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Change</button>  <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Delete</button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <link rel="stylesheet" href= './app/css/app.css'/>
  <link rel="stylesheet" href= './app/css/bootstrap.css'/>
  <link rel="stylesheet" href="./libs/bootstrap-css-only/css/bootstrap.min.css" />
  <script type="text/javascript" src="./libs/angular/angular.min.js"></script>
  <script type="text/javascript" src="./libs/angular-resource/angular-resource.min.js"></script>
  <script type="text/javascript" src="./libs/angular-spring-data-rest/dist/angular-spring-data-rest.min.js"></script>
  <script type="text/javascript" src="./libs/lodash/dist/lodash.min.js"></script>
  <script type="text/javascript" src='./app/js/app.js' ></script>
  <script type="text/javascript" src='./app/js/service/user_service.js' ></script>
  <script type="text/javascript" src='./app/js/controller/user_controller.js'></script>
  </body>
</html>