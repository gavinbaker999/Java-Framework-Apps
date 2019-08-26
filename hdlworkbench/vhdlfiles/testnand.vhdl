entity testnand is
port(in1:in bit;in2:in bit;out1:out bit);
end testnand;
architecture testnandhdl of testnand is
component nandgate
port(in1:in bit;in2:in bit;out1:out bit);
end component;
for u1 : nandgate use entity work.nandgate(nandgatehdl);
u1 : nandgate port map (in1,in2,out1);
begin
signal ck : bit;
clockpluse: process(ck)
begin
if ck='1' and ck'last_value='0' then
	ck<='0' after 20ms;
else
	ck<='1' after 20ms;
end if;
end process clockpluse;
process
begin
wait on ck;
end process;
procedure testnand_testproc(signal clock:in std_logic) is
begin
variable gavin : qsim_state_vector(0 to 3);
signal pc : qsim_state_vector(0 to 3);
file infile : text is in "infile";
if clock'last_value='1' then
pc<="0011" after 10ms;
end if;
end procedure testnand_testproc;
end testnandhdl;
force(testnand,in1,0,1);
force(testnand,in2,0,1);
force(testnand,ck,0,1);
force(testnand,in1,50,0);
force(testnand,in2,50,0);
