create or replace package querystu is

  -- Author  : LIYING-SALES
  -- Created : 2019/2/17 17:32:06
  -- Purpose : querystu
  
  -- Public type declarations
  type cstu is ref cursor;
  
  -- Public constant declarations
  -- <ConstantName> constant <Datatype> := <Value>;

  -- Public variable declarations
  -- <VariableName> <Datatype>;

  -- Public function and procedure declarations
  procedure querystubysex(ssex in varchar2,stuList out cstu);

end querystu;
/
create or replace package body querystu is

  procedure querystubysex(ssex in varchar2,stuList out cstu)
  as
  begin
    open stuList for select * from student where sex = ssex;

  end querystubysex;
end querystu;
/
