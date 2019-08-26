entity nandgate is
port(in1:in bit;in2:in bit;out1:out bit);
end nandgate;
architecture nandgatehdl of nandgate is
begin
nandgate_two: process(in1,in2)
begin
if in1='1' and in2='1' then
	out1<='0';
else
	out1<='1';
end if;
end process nandgate_two;
end nandgatehdl;

