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

    scareUser();
    askIfScared();
    
}

function askIfScared() {
    const scaredText = document.getElementById("scaredText");
    scaredText.innerHTML = `Sorry... did I ${'scare'.bold().italics()} you?`;

    const answerButtons = document.getElementById('yes-no-buttons');
    answerButtons.innerHTML = "<button class='button-style pure-button yes-no-buttons-style' onclick='apologizeAndGiveRose()'> YES! How could you? </button> <button class='button-style pure-button yes-no-buttons-style' onclick='tryHarderAndGiveDaisy()'> NO! I thought you loved me...</button>";

}

function scareUser() {
    const booText = document.getElementById('boo-text');
    booText.innerText = "Boo! ;)";
    booText.classList.add('animate__animated', 'animate__tada');

    document.getElementById("ghost-image-one").src = "https://thumbs.gfycat.com/AbleAngelicAphid-size_restricted.gif";
    document.getElementById("ghost-image-two").src = "https://i.ya-webdesign.com/images/ghost-gif-png.gif";

}

function apologizeAndGiveRose() {
    const apologyText = document.getElementById("apology-text");
    apologyText.innerText = "I apologize... Here is a rose";
    document.getElementById("flower-image").src = "https://media.giphy.com/media/1ajPOWvok41qDSIuBy/giphy.gif";

}

function tryHarderAndGiveDaisy() {
    const apologyText = document.getElementById("apology-text");
    apologyText.innerText = "I'll try harder next time... Here is a Daisy";
    document.getElementById("flower-image").src = "https://webstockreview.net/images/daisy-clipart-flower-gif-2.gif";

}
