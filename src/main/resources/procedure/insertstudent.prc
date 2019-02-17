create or replace procedure insertstudent(sid in number, sname in varchar2, ssex in char)
as
  id_invalid exception;
  name_invalid exception;
  sex_invalid exception;
  cursor cstu is select id from student where id = sid;
  csid student.id%type;
  stuTotal number;
begin
  -- 校验参数
  -- 参数不能为空
  if sid is null then raise id_invalid;
  elsif trim(sname) is null then raise name_invalid;
  elsif trim(ssex) is null then raise sex_invalid;
  end if;
  
  open cstu;
  -- 检验id不重复
  fetch cstu into csid;
  if cstu%found then raise id_invalid;
  end if;
  close cstu;
  
  -- 检验姓名规定长度
  if length(sname)>10 then raise name_invalid;
  
  -- 检验性别只能是 ‘M’ 或者 ‘F’
  elsif ssex not in('M','F') then raise sex_invalid;
  end if;
  insert into student values(sid,sname,ssex);
  select count(*) into stuTotal from student; 
  commit;
  dbms_output.put_line('insert success! now there is '|| stuTotal || ' students in database!');
  
  -- 插入数据
exception
  when id_invalid then dbms_output.put_line('学号参数错误');
  when name_invalid then dbms_output.put_line('名字参数错误');  
  when sex_invalid then dbms_output.put_line('性别参数错误');  
  when others then dbms_output.put_line('other exception!');  
end insertstudent;
/
