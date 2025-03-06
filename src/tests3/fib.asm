.data
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
str_2308920517:
.asciiz "Enter n for fibonacci:\n"
# Allocated labels for virtual registers
.align 2
label_63_v24:
.space 4
.align 2
label_14_v0:
.space 4
.align 2
label_31_v8:
.space 4
.align 2
label_74_v30:
.space 4
.align 2
label_66_v26:
.space 4
.align 2
label_69_v27:
.space 4
.align 2
label_57_v21:
.space 4
.align 2
label_54_v20:
.space 4
.align 2
label_38_v11:
.space 4
.align 2
label_27_v6:
.space 4
.align 2
label_34_v10:
.space 4
.align 2
label_58_v22:
.space 4
.align 2
label_73_v29:
.space 4
.align 2
label_48_v17:
.space 4
.align 2
label_64_v25:
.space 4
.align 2
label_45_v16:
.space 4
.align 2
label_18_v2:
.space 4
.align 2
label_32_v9:
.space 4
.align 2
label_70_v28:
.space 4
.align 2
label_76_v31:
.space 4
.align 2
label_52_v19:
.space 4
.align 2
label_42_v14:
.space 4
.align 2
label_22_v4:
.space 4
.align 2
label_17_v1:
.space 4
.align 2
label_24_v5:
.space 4
.align 2
label_47_v15:
.space 4
.align 2
label_37_v12:
.space 4
.align 2
label_20_v3:
.space 4
.align 2
label_51_v18:
.space 4
.align 2
label_78_v32:
.space 4
.align 2
label_60_v23:
.space 4
.align 2
label_40_v13:
.space 4
.align 2
label_28_v7:
.space 4
# Allocated labels for virtual registers
.align 2
label_80_v33:
.space 4
.align 2
label_84_v35:
.space 4
.align 2
label_87_v37:
.space 4
.align 2
label_92_v40:
.space 4
.align 2
label_81_v34:
.space 4
.align 2
label_89_v38:
.space 4
.align 2
label_91_v39:
.space 4
.align 2
label_85_v36:
.space 4

.text
fib:
# Original instruction: andi $sp,$sp,-4
andi $sp,$sp,-4
# Align sp to word boundary
# Original instruction: addiu $sp,$sp,-8
addiu $sp,$sp,-8
# Original instruction: sw $fp,4($sp)
sw $fp,4($sp)
# Original instruction: sw $ra,0($sp)
sw $ra,0($sp)
# Original instruction: add $fp,$sp,$zero
add $fp,$sp,$zero
# Original instruction: addiu $sp,$sp,-20
addiu $sp,$sp,-20
# Original instruction: addiu v0,$fp,8
addiu $t5,$fp,8
la $t0,label_14_v0
sw $t5,0($t0)
# Original instruction: lw v1,0(v0)
la $t5,label_14_v0
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_17_v1
sw $t4,0($t0)
# Original instruction: li v2,2
li $t5,2
la $t0,label_18_v2
sw $t5,0($t0)
# Original instruction: slt v3,v1,v2
la $t5,label_17_v1
lw $t5,0($t5)
la $t4,label_18_v2
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_20_v3
sw $t3,0($t0)
# Original instruction: beqz v3,label_6_if_else
la $t5,label_20_v3
lw $t5,0($t5)
beqz $t5,label_6_if_else
# Original instruction: addiu v4,$fp,8
addiu $t5,$fp,8
la $t0,label_22_v4
sw $t5,0($t0)
# Original instruction: lw v5,0(v4)
la $t5,label_22_v4
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_24_v5
sw $t4,0($t0)
# Original instruction: add $v0,v5,$zero
la $t5,label_24_v5
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_fib
j label_5_return_fib
# Original instruction: j label_7_if_end
j label_7_if_end
label_6_if_else:
label_7_if_end:
# Original instruction: li v6,0
li $t5,0
la $t0,label_27_v6
sw $t5,0($t0)
# Original instruction: addiu v7,$fp,-12
addiu $t5,$fp,-12
la $t0,label_28_v7
sw $t5,0($t0)
# Original instruction: sw v6,0(v7)
la $t5,label_27_v6
lw $t5,0($t5)
la $t4,label_28_v7
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v8,1
li $t5,1
la $t0,label_31_v8
sw $t5,0($t0)
# Original instruction: addiu v9,$fp,-16
addiu $t5,$fp,-16
la $t0,label_32_v9
sw $t5,0($t0)
# Original instruction: sw v8,0(v9)
la $t5,label_31_v8
lw $t5,0($t5)
la $t4,label_32_v9
lw $t4,0($t4)
sw $t5,0($t4)
label_8_while_start:
# Original instruction: addiu v10,$fp,8
addiu $t5,$fp,8
la $t0,label_34_v10
sw $t5,0($t0)
# Original instruction: lw v11,0(v10)
la $t5,label_34_v10
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_38_v11
sw $t4,0($t0)
# Original instruction: li v12,1
li $t5,1
la $t0,label_37_v12
sw $t5,0($t0)
# Original instruction: slt v13,v12,v11
la $t5,label_37_v12
lw $t5,0($t5)
la $t4,label_38_v11
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_40_v13
sw $t3,0($t0)
# Original instruction: beqz v13,label_9_while_exit
la $t5,label_40_v13
lw $t5,0($t5)
beqz $t5,label_9_while_exit
# Original instruction: addiu v14,$fp,-12
addiu $t5,$fp,-12
la $t0,label_42_v14
sw $t5,0($t0)
# Original instruction: lw v15,0(v14)
la $t5,label_42_v14
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_47_v15
sw $t4,0($t0)
# Original instruction: addiu v16,$fp,-16
addiu $t5,$fp,-16
la $t0,label_45_v16
sw $t5,0($t0)
# Original instruction: lw v17,0(v16)
la $t5,label_45_v16
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_48_v17
sw $t4,0($t0)
# Original instruction: add v18,v15,v17
la $t5,label_47_v15
lw $t5,0($t5)
la $t4,label_48_v17
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_51_v18
sw $t3,0($t0)
# Original instruction: addiu v19,$fp,-20
addiu $t5,$fp,-20
la $t0,label_52_v19
sw $t5,0($t0)
# Original instruction: sw v18,0(v19)
la $t5,label_51_v18
lw $t5,0($t5)
la $t4,label_52_v19
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v20,$fp,-16
addiu $t5,$fp,-16
la $t0,label_54_v20
sw $t5,0($t0)
# Original instruction: lw v21,0(v20)
la $t5,label_54_v20
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_57_v21
sw $t4,0($t0)
# Original instruction: addiu v22,$fp,-12
addiu $t5,$fp,-12
la $t0,label_58_v22
sw $t5,0($t0)
# Original instruction: sw v21,0(v22)
la $t5,label_57_v21
lw $t5,0($t5)
la $t4,label_58_v22
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v23,$fp,-20
addiu $t5,$fp,-20
la $t0,label_60_v23
sw $t5,0($t0)
# Original instruction: lw v24,0(v23)
la $t5,label_60_v23
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_63_v24
sw $t4,0($t0)
# Original instruction: addiu v25,$fp,-16
addiu $t5,$fp,-16
la $t0,label_64_v25
sw $t5,0($t0)
# Original instruction: sw v24,0(v25)
la $t5,label_63_v24
lw $t5,0($t5)
la $t4,label_64_v25
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v26,$fp,8
addiu $t5,$fp,8
la $t0,label_66_v26
sw $t5,0($t0)
# Original instruction: lw v27,0(v26)
la $t5,label_66_v26
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_69_v27
sw $t4,0($t0)
# Original instruction: li v28,1
li $t5,1
la $t0,label_70_v28
sw $t5,0($t0)
# Original instruction: sub v29,v27,v28
la $t5,label_69_v27
lw $t5,0($t5)
la $t4,label_70_v28
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_73_v29
sw $t3,0($t0)
# Original instruction: addiu v30,$fp,8
addiu $t5,$fp,8
la $t0,label_74_v30
sw $t5,0($t0)
# Original instruction: sw v29,0(v30)
la $t5,label_73_v29
lw $t5,0($t5)
la $t4,label_74_v30
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: j label_8_while_start
j label_8_while_start
label_9_while_exit:
# Original instruction: addiu v31,$fp,-16
addiu $t5,$fp,-16
la $t0,label_76_v31
sw $t5,0($t0)
# Original instruction: lw v32,0(v31)
la $t5,label_76_v31
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_78_v32
sw $t4,0($t0)
# Original instruction: add $v0,v32,$zero
la $t5,label_78_v32
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_fib
j label_5_return_fib
label_5_return_fib:
# Original instruction: addiu $sp,$sp,20
addiu $sp,$sp,20
# Original instruction: lw $ra,0($sp)
lw $ra,0($sp)
# Original instruction: lw $fp,4($sp)
lw $fp,4($sp)
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: jr $ra
jr $ra

.text
.globl main
main:
# Original instruction: andi $sp,$sp,-4
andi $sp,$sp,-4
# Align sp to word boundary
# Original instruction: addiu $sp,$sp,-8
addiu $sp,$sp,-8
# Original instruction: sw $fp,4($sp)
sw $fp,4($sp)
# Original instruction: sw $ra,0($sp)
sw $ra,0($sp)
# Original instruction: add $fp,$sp,$zero
add $fp,$sp,$zero
# Original instruction: addiu $sp,$sp,-12
addiu $sp,$sp,-12
# Original instruction: la v33,str_2308920517
la $t5,str_2308920517
la $t0,label_80_v33
sw $t5,0($t0)
# Original instruction: add $a0,v33,$zero
la $t5,label_80_v33
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v34,$zero,$zero
add $t5,$zero,$zero
la $t0,label_81_v34
sw $t5,0($t0)
# Original instruction: li $v0,5
li $v0,5
# Original instruction: syscall
syscall
# Original instruction: add v35,$v0,$zero
add $t5,$v0,$zero
la $t0,label_84_v35
sw $t5,0($t0)
# Original instruction: addiu v36,$fp,-12
addiu $t5,$fp,-12
la $t0,label_85_v36
sw $t5,0($t0)
# Original instruction: sw v35,0(v36)
la $t5,label_84_v35
lw $t5,0($t5)
la $t4,label_85_v36
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v37,$fp,-12
addiu $t5,$fp,-12
la $t0,label_87_v37
sw $t5,0($t0)
# Original instruction: lw v38,0(v37)
la $t5,label_87_v37
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_89_v38
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v38,0($sp)
la $t5,label_89_v38
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal fib
jal fib
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v39,$v0,$zero
add $t5,$v0,$zero
la $t0,label_91_v39
sw $t5,0($t0)
# Original instruction: add $a0,v39,$zero
la $t5,label_91_v39
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v40,$zero,$zero
add $t5,$zero,$zero
la $t0,label_92_v40
sw $t5,0($t0)
label_11_return_main:
# Original instruction: addiu $sp,$sp,12
addiu $sp,$sp,12
# Original instruction: lw $ra,0($sp)
lw $ra,0($sp)
# Original instruction: lw $fp,4($sp)
lw $fp,4($sp)
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: li $v0,10
li $v0,10
# Original instruction: syscall
syscall

