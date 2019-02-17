create or replace procedure querystudentsex
is
  cursor cstu is select sex,count(*) num from student group by sex;
  ssex student.sex%type;
  snum number;  
begin
  open cstu;
  loop
  fetch cstu into ssex,snum;
  exit when cstu%notfound;
  
  dbms_output.put_line(ssex ||' num is:' || snum);
  
  end loop;
  close cstu;
  
  
end querystudentsex;
/
