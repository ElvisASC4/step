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

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
    const greetings =
        ["Hello, you look cute today. Lets be cute together!", "¡Hola Mundo! Soy Dominicano", "你好，世界！<3 ", "Bonjour le monde! Je t'aime "];

    // Pick a random greeting.
    const greeting = greetings[Math.floor(Math.random() * greetings.length)];

    // Add it to the page.
    const greetingContainer = document.getElementById('greeting-container');
    greetingContainer.innerText = greeting;

    const booText = document.getElementById('boo-text');
    booText.innerText = "Boo! ;)";

    //const element = document.querySelector('.my-element');
    booText.classList.add('animate__animated', 'animate__tada');

    const ghostImage1 = document.getElementById("ghost-image-one").src = "https://thumbs.gfycat.com/AbleAngelicAphid-size_restricted.gif";
    const ghostImage2 = document.getElementById("ghost-image-two").src = "https://i.ya-webdesign.com/images/ghost-gif-png.gif";

}
