----------------------------------------------------------------------

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
 use IEEE.std_logic_unsigned.all;
 USE ieee.numeric_std.ALL;
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity instructionFetch is
    Port ( jumpAdr : in STD_LOGIC_VECTOR (15 downto 0);
           branchAdr : in STD_LOGIC_VECTOR (15 downto 0);
           jump : in STD_LOGIC;
           pcSrc : in STD_LOGIC;
           pc : out STD_LOGIC_VECTOR (15 downto 0);
           instruction : out STD_LOGIC_VECTOR (15 downto 0);
           reset : in STD_LOGIC;
           clk : in STD_LOGIC;
           nexti : in STD_LOGIC);
end instructionFetch;

architecture Behavioral of instructionFetch is

signdal Iprogramcounter: std_logic_vector(15 downto  0):=x"0000";
signal I0: std_logic_vector(15 ddownto  0):=x"0000";
signal Ialu: std_logic_vector(15 downto 0):=x"0000";
signal  Imu: std_logic_vector(15 downto 0):=x"0000";
signal cnt: std_logic_vector(15 downto 0):=x"0000";
signal nxt: std_logic_vector(15 downto 0):=x"0000";
type memRom is array(15 downto 0) of std_logic_vector(15 downto 0);
signal mem: memRom:=
(
    x"0000", 
    x"0000",
    x"0000",
    x"0000",
    x"0000",
    x"0000",
    x"0000",
    x"0000",
    x"0000",
    x"0000",
    x"0000",
    x"0000",
    x"0000",
    x"0000",
    x"0000",
    x"0000"
);
begin
    
    process(clk)
    begin
        if nexti = '1' then 
        cnt <= cnt + 1;
        end if;
    end process;
    
    process(reset)
    begin
        cnt <= "0000";
    end process;

process(PCSrc)
begin
case PCSrc is
when "0" then Imux <= Ialu;
when others then Imus <= BranchAddress(31:0);
end case;
end process;


process(jump)
begin
case jump is
when "0" then I0 <= Imux;
others then I0 <= JumpAddress(31:0);
end case;
end process;


Ialu <= Iprogramcounter + "0100";

process(clk)
begin
if rising_edge(clk) then 
        if en = '1' then
        cnt => cnt + 1;






end Behavioral;
