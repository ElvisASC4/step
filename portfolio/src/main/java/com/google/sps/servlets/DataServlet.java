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

import java.io.IOException;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;



/** Servlet that returns an Array of comments as JSON. TODO: modify this file to handle comments data from user input */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message;
         
        ArrayList<String> greetings = new ArrayList<>();
        greetings.add("Hey cutie, I like your hoodie. Dont lose the goodies");
        greetings.add("Hey cutie, I like your hat. Careful with the bats");
        greetings.add("Hey cutie, I like your anti-capitalist shirt, lets go take down the bourgeoisie");
        
        String json = convertToJsonUsingGson(greetings);

        response.setContentType("application/json;");
        response.getWriter().println(json);
    }

    private String convertToJson(ArrayList greetings) {
        Gson gson = new Gson();
        String json = gson.toJson(greetings);
        return json;
    }
}
