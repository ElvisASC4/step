// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.users.UserService;
import com.google.gson.Gson;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;


@WebServlet("/loggedIn")
public class LogInServlet extends HttpServlet {

        public class LoggedInStatus {
        private boolean loggedIn;
        private String email;
        private String logoutUrl;
        private String loginUrl;

        public LoggedInStatus(boolean loggedIn) {
            this.loggedIn = loggedIn;
            this.email = email;
            this.loginUrl = loginUrl;
            this.logoutUrl = logoutUrl;
        }

        public void setEmail(String email){
            this.email = email;
        }

        public void setLogoutUrl(String logoutUrl){
            this.logoutUrl = logoutUrl;
        }

        public void setLoginUrl(String loginUrl){
            this.loginUrl = loginUrl;
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        UserService userService = UserServiceFactory.getUserService();
        if (userService.isUserLoggedIn()) {
            String userEmail = userService.getCurrentUser().getEmail();
            String urlToRedirectToAfterUserLogsOut = "/index.html";
            String logoutUrl = userService.createLogoutURL(urlToRedirectToAfterUserLogsOut);
            String urlToRedirectToAfterUserLogsIn = "/index.html";
            String loginUrl = userService.createLoginURL(urlToRedirectToAfterUserLogsIn);

            System.out.println("<p>Hello " + userEmail + "!</p>");
            System.out.println("<p>Logout <a href=\"" + logoutUrl + "\">here</a>.</p>");

            LoggedInStatus loggedIn = new LoggedInStatus(true);
            loggedIn.setEmail(userEmail);
            loggedIn.setLogoutUrl(logoutUrl);
            loggedIn.setLoginUrl(loginUrl);

            String json = convertToJson(loggedIn);
            response.setContentType("application/json;");
            response.getWriter().println(json);
        } else {
            String urlToRedirectToAfterUserLogsIn = "/index.html";
            String loginUrl = userService.createLoginURL(urlToRedirectToAfterUserLogsIn);

            System.out.println("<p>Hello stranger.</p>");
            System.out.println("<p>Login <a href=\"" + loginUrl + "\">here</a>.</p>");

            LoggedInStatus loggedIn = new LoggedInStatus(false);
            loggedIn.setLoginUrl(loginUrl);

            String json = convertToJson(loggedIn);
            response.setContentType("application/json;");
            response.getWriter().println(json);
        }
    }

        private String convertToJson(LoggedInStatus comment) {
            Gson gson = new Gson();
            String json = gson.toJson(comment);
            return json;
        }
    }