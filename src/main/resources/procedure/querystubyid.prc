create or replace procedure querystubyid(sid in number, sname out varchar2, ssex out varchar2) is
begin

  select name,sex into sname,ssex from student where id = sid;

exception
  when no_data_found then dbms_output.put_line('no data found');
  when others then dbms_output.put_line('other exception');

end querystubyid;
/
