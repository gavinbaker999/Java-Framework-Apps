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
procedure testnand_testproc(clock:in std_logic) is
begin
variable gavin : qsim_state_vector(0 to 3);
signal pc : qsim_state_vector(0 to 3);
variable bryn : bit;
file infile : text is in "infile";
signal ck3 : bit;
if ck3'last_value='1' then
pc<=pc+"1010";
pc<="0011" after 10ms;
end if;
end procedure testnand_testproc;
end testnandhdl;
force(testnand,in1,0,1);
and ui
force(testnand,in2,0,1);
I love JF
force(testnand,in1,50,0);
force(testnand,in2,50,0);



