.data
.align 2
global_n:
.space 4
.align 2
global_c:
.space 1
.align 2
global_size:
.space 4
.align 2
global_s:
.space 4
.align 2
global_i:
.space 4
# Spill slot for v179
.align 2
label_12_spill_v179:
.space 4
# Spill slot for v6
.align 2
label_11_spill_v6:
.space 4
# Spill slot for v3
.align 2
label_13_spill_v3:
.space 4
# Spill slot for v4
.align 2
label_14_spill_v4:
.space 4
# Spill slot for v2
.align 2
label_16_spill_v2:
.space 4
# Spill slot for v187
.align 2
label_15_spill_v187:
.space 4

.text
rec:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-68
addiu $t0,$fp,8
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,0
# store spilled v2 from v203
la $t1,label_16_spill_v2
sw $t0,0($t1)
lw $t0,0($sp)
# store spilled v187 from v199
la $t1,label_15_spill_v187
sw $t0,0($t1)
# store spilled v3 from v187
la $t1,label_13_spill_v3
# load spilled v187 into v201
la $t0,label_15_spill_v187
lw $t0,0($t0)
sw $t0,0($t1)
addiu $sp,$sp,4
# load spilled v2 into v205
la $t0,label_16_spill_v2
lw $t0,0($t0)
add $t1,$t0,$zero
# store spilled v4 from v193
la $t0,label_14_spill_v4
sw $t1,0($t0)
# load spilled v3 into v189
la $t0,label_13_spill_v3
lw $t0,0($t0)
# load spilled v4 into v195
la $t1,label_14_spill_v4
lw $t1,0($t1)
slt $t0,$t0,$t1
# store spilled v179 from v183
la $t1,label_12_spill_v179
sw $t0,0($t1)
# store spilled v6 from v179
la $t0,label_11_spill_v6
# load spilled v179 into v185
la $t1,label_12_spill_v179
lw $t1,0($t1)
sw $t1,0($t0)
# load spilled v3 into v191
la $t0,label_13_spill_v3
lw $t0,0($t0)
# load spilled v4 into v197
la $t1,label_14_spill_v4
lw $t1,0($t1)
slt $t1,$t1,$t0
# load spilled v6 into v181
la $t0,label_11_spill_v6
lw $t0,0($t0)
or $t0,$t0,$t1
xori $t0,$t0,1
beqz $t0,label_7_if_else
li $t1,111
addiu $t0,$fp,-12
sw $t1,0($t0)
li $t1,222
addiu $t0,$fp,-16
sw $t1,0($t0)
li $t1,333
addiu $t0,$fp,-20
sw $t1,0($t0)
li $t1,444
addiu $t0,$fp,-24
sw $t1,0($t0)
li $t1,555
addiu $t0,$fp,-28
sw $t1,0($t0)
addiu $t0,$fp,-12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-16
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
addiu $t1,$fp,-32
sw $t0,0($t1)
addiu $t0,$fp,-20
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-24
lw $t1,0($t0)
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
add $t1,$t0,$t1
addiu $t0,$fp,-36
sw $t1,0($t0)
addiu $t0,$fp,-28
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,10
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
addiu $t1,$fp,-40
sw $t0,0($t1)
addiu $t0,$fp,-32
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-36
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t1,$t1,$t0
addiu $t0,$fp,-44
sw $t1,0($t0)
addiu $t0,$fp,-40
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-44
lw $t1,0($t0)
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
add $t1,$t0,$t1
addiu $t0,$fp,-48
sw $t1,0($t0)
addiu $t0,$fp,-48
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,5
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t1,$t1,$t0
addiu $t0,$fp,-52
sw $t1,0($t0)
addiu $t0,$fp,-52
lw $t1,0($t0)
addiu $t0,$fp,-56
sw $t1,0($t0)
addiu $t0,$fp,-56
lw $t0,0($t0)
addiu $t1,$fp,-60
sw $t0,0($t1)
addiu $t0,$fp,-60
lw $t1,0($t0)
addiu $t0,$fp,-64
sw $t1,0($t0)
addiu $t0,$fp,-64
lw $t1,0($t0)
addiu $t0,$fp,-68
sw $t1,0($t0)
j label_8_if_end
label_7_if_else:
addiu $t0,$fp,8
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t1,1
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
sub $t0,$t0,$t1
addiu $sp,$sp,-4
sw $t0,0($sp)
jal rec
addiu $sp,$sp,4
add $t1,$v0,$zero
addiu $t0,$fp,-12
sw $t1,0($t0)
addiu $t0,$fp,-12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t1,1
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
add $t0,$t0,$t1
addiu $t1,$fp,-16
sw $t0,0($t1)
addiu $t0,$fp,-12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,2
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
addiu $t1,$fp,-20
sw $t0,0($t1)
addiu $t0,$fp,8
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,1
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
sub $t0,$t1,$t0
addiu $sp,$sp,-4
sw $t0,0($sp)
jal rec
addiu $sp,$sp,4
add $t0,$v0,$zero
addiu $t1,$fp,-24
sw $t0,0($t1)
addiu $t0,$fp,-20
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-24
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
addiu $t1,$fp,-28
sw $t0,0($t1)
addiu $t0,$fp,-28
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-12
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
addiu $t1,$fp,-32
sw $t0,0($t1)
addiu $t0,$fp,-32
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t1,3
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
add $t0,$t0,$t1
addiu $t1,$fp,-36
sw $t0,0($t1)
addiu $t0,$fp,-36
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,4
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
addiu $t1,$fp,-40
sw $t0,0($t1)
addiu $t0,$fp,-40
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t1,5
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
add $t1,$t0,$t1
addiu $t0,$fp,-44
sw $t1,0($t0)
addiu $t0,$fp,-44
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-12
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t1,$t1,$t0
addiu $t0,$fp,-48
sw $t1,0($t0)
addiu $t0,$fp,-48
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,6
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t1,$t1,$t0
addiu $t0,$fp,-52
sw $t1,0($t0)
addiu $t0,$fp,-52
lw $t0,0($t0)
addiu $t1,$fp,-56
sw $t0,0($t1)
addiu $t0,$fp,-56
lw $t0,0($t0)
addiu $t1,$fp,-60
sw $t0,0($t1)
addiu $t0,$fp,-60
lw $t1,0($t0)
addiu $t0,$fp,-64
sw $t1,0($t0)
addiu $t0,$fp,-64
lw $t1,0($t0)
addiu $t0,$fp,-68
sw $t1,0($t0)
label_8_if_end:
addiu $t0,$fp,-68
lw $t0,0($t0)
add $v0,$t0,$zero
j label_6_return_rec
label_6_return_rec:
addiu $sp,$sp,68
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
jr $ra

.text
.globl main
main:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-12
li $t0,1
addiu $sp,$sp,-4
sw $t0,0($sp)
jal rec
addiu $sp,$sp,4
add $t0,$v0,$zero
addiu $t1,$fp,-12
sw $t0,0($t1)
addiu $t0,$fp,-12
lw $t0,0($t0)
add $a0,$t0,$zero
li $v0,1
syscall
add $t0,$zero,$zero
li $t0,0
add $v0,$t0,$zero
j label_10_return_main
label_10_return_main:
addiu $sp,$sp,12
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
li $v0,10
syscall

