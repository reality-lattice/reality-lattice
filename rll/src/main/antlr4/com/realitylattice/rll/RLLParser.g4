/*
 * Copyright 2018 Jonathan Shaw Wood.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

parser grammar RLLParser;
options { tokenVocab=RLLLexer; }

command 
 : action target
  ;

action
  : RLL_CREATE | RLL_UPDATE | RLL_DELETE
  ;

target
  : RLL_TILE | RLL_ITEM
  ;

/*
roll
  : [0-9]'D'[0-9] 
*/

