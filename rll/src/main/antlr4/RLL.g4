

grammar RLL;

command
  : action target
  ;

action
  : 'create' | 'update' | 'delete'
  ;

target
  : 'tile' | 'item'
  ;
