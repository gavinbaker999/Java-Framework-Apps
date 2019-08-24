HDL Work Bench Readme
=====================

1) else, elsif and end if keywords must appear at the beginning of a new line.

2) In a translate table entry check for an integer then a character then a string.

3) Each VHDL statment must appear on a seperate line.

4) In the translation tables a match to string, timeunit or dontcare will always succed.

5) Any thing before the entity keyword will break the symbol table scope rules.

6) Any 'else unaffected' statments are ignored.

7) Signal, variable and constant identifiers have to be unique within an entity.

8) There can only be one signal or variable on the LHS of an expression or defination statment

9) Port map - named association - NYI.

10) Port map - open (unconnected) keyword - NYI.

11) Assume the delta delay is equal to a simulation clock tick

12) Resoultion functions (multiple drivers) - NYI.

13) Keywords (unless special) are assummed to be first token on the line unless the first token is a lne label.

14) Standard functions are added to the symbol table with scope "global" and value "".

15) VHDL'93 language standard is used (expect for FILE?).

16) end statments must include the optional parts, e.g. end process processname.

17) Functions and procedures must be defined before use.

18) The 'others' signal group identifier - NYI.

19) Default mode for procedure/function parameters is 'in'.

20) Multiple parameter defination NYI.

21) Parameter modifiers NYI.

22) Parameter mode must be specified.

23) Types and subtypes must be declared before use.

24) Complier does not check validty of types and subtypes - NYI.

25) hdlWBSignalsUser always defaults to 'default'.

26) Only TEXT file type supported.

27) If we have multiple statments in a WHEN clause they all must appear on one line sperated by ;s

28) Attributes can not appear as first token on line

29) VHDL labels just output the PHP line label:

30) Parameter names by association NYI.

31) Multiple statments per line NYI.

32) Just one attribute per code line - see preProccessAttributes(...)

33) 

34) Forces can only be applied to top level entities.

35) HDL Workbench smulation runs are a single user processes until we implement an entity locking scheme.

36) Create signal-signal map only accepts 'in' and 'out' signal modes.

37) Can't add signals to variables and vice-versa.

38) Remove qualified expressions - NYI.

39) Signals last_value defaults to 'X' when first created.

40) last_event(...) and last_active(...) will return -1 if no event or active yet.

41) All VHDL entities must fit on one GC design sheet and are all if a fixed size.

42) Process not run once before simulation starts?

43) Subtypes can only be nested to one level deep.

44) Optional parameters for some attributes - NYI.

45) Arrays of signals - NYI.

46) Condtion part of an 'if' statment can not be an array expression.

47) Array names must be unquine to the entity.

48) 'attribute' and 'library' keywords are accepted but produce no output.

49) The following keywords are accepted but have no effect -:
    postponed, register, bus, pure, impure, guarded, transport, is, inertial, shared and reject.

50) 'configuration', 'units', 'group', 'alias', 'generic', 'generic map' and 'generate' keywords - NYI.

(c) 2008-2016 End House Software

