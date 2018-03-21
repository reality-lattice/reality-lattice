

grammar RLL;

@header {
package com.realitylattice.rll; 
}

command
  : action target
  ;

action
  : 'create' | 'update' | 'delete'
  ;

target
  : 'tile' | 'item'
  ;

