create or replace procedure querystudent(sid in number) 
as
  studentid student.id%type;
  username student.name%type;
  ssex student.sex%type;
begin
  select id,name,sex into studentid,username,ssex from student where id = sid;
  
  dbms_output.put_line(studentid);
  dbms_output.put_line(username);
  dbms_output.put_line(ssex);
  
  
end querystudent;
/
