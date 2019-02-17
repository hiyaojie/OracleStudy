create or replace procedure insertstudent(sid in number, sname in varchar2, ssex in char)
as
  id_invalid exception;
  name_invalid exception;
  sex_invalid exception;
  cursor cstu is select id from student where id = sid;
  csid student.id%type;
  stuTotal number;
begin
  -- У�����
  -- ��������Ϊ��
  if sid is null then raise id_invalid;
  elsif trim(sname) is null then raise name_invalid;
  elsif trim(ssex) is null then raise sex_invalid;
  end if;
  
  open cstu;
  -- ����id���ظ�
  fetch cstu into csid;
  if cstu%found then raise id_invalid;
  end if;
  close cstu;
  
  -- ���������涨����
  if length(sname)>10 then raise name_invalid;
  
  -- �����Ա�ֻ���� ��M�� ���� ��F��
  elsif ssex not in('M','F') then raise sex_invalid;
  end if;
  insert into student values(sid,sname,ssex);
  select count(*) into stuTotal from student; 
  commit;
  dbms_output.put_line('insert success! now there is '|| stuTotal || ' students in database!');
  
  -- ��������
exception
  when id_invalid then dbms_output.put_line('ѧ�Ų�������');
  when name_invalid then dbms_output.put_line('���ֲ�������');  
  when sex_invalid then dbms_output.put_line('�Ա��������');  
  when others then dbms_output.put_line('other exception!');  
end insertstudent;
/
