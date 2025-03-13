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
# Allocated labels for virtual registers
.align 2
label_24_v6:
.space 4
.align 2
label_21_v4:
.space 4
.align 2
label_13_v0:
.space 4
.align 2
label_31_v9:
.space 4
.align 2
label_15_v1:
.space 4
.align 2
label_27_v7:
.space 4
.align 2
label_20_v3:
.space 4
.align 2
label_26_v5:
.space 4
.align 2
label_18_v2:
.space 4
.align 2
label_29_v8:
.space 4
# Allocated labels for virtual registers
.align 2
label_56_v21:
.space 4
.align 2
label_33_v10:
.space 4
.align 2
label_65_v26:
.space 4
.align 2
label_40_v14:
.space 4
.align 2
label_43_v16:
.space 4
.align 2
label_41_v13:
.space 4
.align 2
label_69_v28:
.space 4
.align 2
label_53_v20:
.space 4
.align 2
label_38_v12:
.space 4
.align 2
label_47_v17:
.space 4
.align 2
label_59_v23:
.space 4
.align 2
label_61_v24:
.space 4
.align 2
label_67_v27:
.space 4
.align 2
label_45_v15:
.space 4
.align 2
label_51_v19:
.space 4
.align 2
label_63_v25:
.space 4
.align 2
label_58_v22:
.space 4
.align 2
label_49_v18:
.space 4
.align 2
label_35_v11:
.space 4
# Allocated labels for virtual registers
.align 2
label_89_v38:
.space 4
.align 2
label_72_v30:
.space 4
.align 2
label_92_v40:
.space 4
.align 2
label_79_v33:
.space 4
.align 2
label_81_v34:
.space 4
.align 2
label_84_v35:
.space 4
.align 2
label_87_v36:
.space 4
.align 2
label_86_v37:
.space 4
.align 2
label_75_v31:
.space 4
.align 2
label_77_v32:
.space 4
.align 2
label_91_v39:
.space 4
.align 2
label_74_v29:
.space 4

.text
addOne:
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
# Original instruction: addiu $sp,$sp,-8
addiu $sp,$sp,-8
# Original instruction: addiu v0,$fp,8
addiu $t5,$fp,8
la $t0,label_13_v0
sw $t5,0($t0)
# Original instruction: lw v1,0(v0)
la $t5,label_13_v0
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_15_v1
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v1,0($sp)
la $t5,label_15_v1
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: li v2,1
li $t5,1
la $t0,label_18_v2
sw $t5,0($t0)
# Original instruction: lw v3,0($sp)
lw $t5,0($sp)
la $t0,label_20_v3
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v4,v2,$zero
la $t5,label_18_v2
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_21_v4
sw $t4,0($t0)
# Original instruction: add v5,v3,v4
la $t5,label_20_v3
lw $t5,0($t5)
la $t4,label_21_v4
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_26_v5
sw $t3,0($t0)
# Original instruction: addiu v6,$fp,8
addiu $t5,$fp,8
la $t0,label_24_v6
sw $t5,0($t0)
# Original instruction: addiu v7,v6,0
la $t5,label_24_v6
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_27_v7
sw $t4,0($t0)
# Original instruction: sw v5,0(v7)
la $t5,label_26_v5
lw $t5,0($t5)
la $t4,label_27_v7
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v8,$fp,8
addiu $t5,$fp,8
la $t0,label_29_v8
sw $t5,0($t0)
# Original instruction: lw v9,0(v8)
la $t5,label_29_v8
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_31_v9
sw $t4,0($t0)
# Original instruction: sw v9,0($a0)
la $t5,label_31_v9
lw $t5,0($t5)
sw $t5,0($a0)
# Original instruction: j label_5_return_addOne
j label_5_return_addOne
label_5_return_addOne:
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: lw $ra,0($sp)
lw $ra,0($sp)
# Original instruction: lw $fp,4($sp)
lw $fp,4($sp)
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: jr $ra
jr $ra

.text
recurse:
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
# Original instruction: addiu $sp,$sp,-8
addiu $sp,$sp,-8
# Original instruction: addiu v10,$fp,12
addiu $t5,$fp,12
la $t0,label_33_v10
sw $t5,0($t0)
# Original instruction: lw v11,0(v10)
la $t5,label_33_v10
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_35_v11
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v11,0($sp)
la $t5,label_35_v11
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: li v12,0
li $t5,0
la $t0,label_38_v12
sw $t5,0($t0)
# Original instruction: lw v13,0($sp)
lw $t5,0($sp)
la $t0,label_41_v13
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v14,v12,$zero
la $t5,label_38_v12
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_40_v14
sw $t4,0($t0)
# Original instruction: slt v16,v14,v13
la $t5,label_40_v14
lw $t5,0($t5)
la $t4,label_41_v13
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_43_v16
sw $t3,0($t0)
# Original instruction: xori v15,v16,1
la $t5,label_43_v16
lw $t5,0($t5)
xori $t4,$t5,1
la $t0,label_45_v15
sw $t4,0($t0)
# Original instruction: beqz v15,label_8_if_else
la $t5,label_45_v15
lw $t5,0($t5)
beqz $t5,label_8_if_else
# Original instruction: addiu v17,$fp,8
addiu $t5,$fp,8
la $t0,label_47_v17
sw $t5,0($t0)
# Original instruction: lw v18,0(v17)
la $t5,label_47_v17
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_49_v18
sw $t4,0($t0)
# Original instruction: sw v18,0($a0)
la $t5,label_49_v18
lw $t5,0($t5)
sw $t5,0($a0)
# Original instruction: j label_7_return_recurse
j label_7_return_recurse
# Original instruction: j label_9_if_end
j label_9_if_end
label_8_if_else:
label_9_if_end:
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: add $a0,$sp,$zero
add $a0,$sp,$zero
# Original instruction: addiu v19,$fp,12
addiu $t5,$fp,12
la $t0,label_51_v19
sw $t5,0($t0)
# Original instruction: lw v20,0(v19)
la $t5,label_51_v19
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_53_v20
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v20,0($sp)
la $t5,label_53_v20
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: li v21,1
li $t5,1
la $t0,label_56_v21
sw $t5,0($t0)
# Original instruction: lw v22,0($sp)
lw $t5,0($sp)
la $t0,label_58_v22
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v23,v21,$zero
la $t5,label_56_v21
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_59_v23
sw $t4,0($t0)
# Original instruction: sub v24,v22,v23
la $t5,label_58_v22
lw $t5,0($t5)
la $t4,label_59_v23
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_61_v24
sw $t3,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v24,0($sp)
la $t5,label_61_v24
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: add $a0,$sp,$zero
add $a0,$sp,$zero
# Original instruction: addiu v25,$fp,8
addiu $t5,$fp,8
la $t0,label_63_v25
sw $t5,0($t0)
# Original instruction: lw v26,0(v25)
la $t5,label_63_v25
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_65_v26
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v26,0($sp)
la $t5,label_65_v26
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal addOne
jal addOne
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: add v27,$a0,$zero
add $t5,$a0,$zero
la $t0,label_67_v27
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v27,0($sp)
la $t5,label_67_v27
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal recurse
jal recurse
# Original instruction: addiu $sp,$sp,12
addiu $sp,$sp,12
# Original instruction: add v28,$a0,$zero
add $t5,$a0,$zero
la $t0,label_69_v28
sw $t5,0($t0)
# Original instruction: add $v0,v28,$zero
la $t5,label_69_v28
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_7_return_recurse
j label_7_return_recurse
label_7_return_recurse:
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
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
# Original instruction: li v29,0
li $t5,0
la $t0,label_74_v29
sw $t5,0($t0)
# Original instruction: addiu v30,$fp,-12
addiu $t5,$fp,-12
la $t0,label_72_v30
sw $t5,0($t0)
# Original instruction: addiu v31,v30,0
la $t5,label_72_v30
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_75_v31
sw $t4,0($t0)
# Original instruction: sw v29,0(v31)
la $t5,label_74_v29
lw $t5,0($t5)
la $t4,label_75_v31
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: add $a0,$sp,$zero
add $a0,$sp,$zero
# Original instruction: li v32,10
li $t5,10
la $t0,label_77_v32
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v32,0($sp)
la $t5,label_77_v32
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v33,$fp,-12
addiu $t5,$fp,-12
la $t0,label_79_v33
sw $t5,0($t0)
# Original instruction: lw v34,0(v33)
la $t5,label_79_v33
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_81_v34
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v34,0($sp)
la $t5,label_81_v34
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal recurse
jal recurse
# Original instruction: addiu $sp,$sp,12
addiu $sp,$sp,12
# Original instruction: add v35,$a0,$zero
add $t5,$a0,$zero
la $t0,label_84_v35
sw $t5,0($t0)
# Original instruction: addiu v36,$fp,-12
addiu $t5,$fp,-12
la $t0,label_87_v36
sw $t5,0($t0)
# Original instruction: lw v37,0(v35)
la $t5,label_84_v35
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_86_v37
sw $t4,0($t0)
# Original instruction: sw v37,0(v36)
la $t5,label_86_v37
lw $t5,0($t5)
la $t4,label_87_v36
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v38,$fp,-12
addiu $t5,$fp,-12
la $t0,label_89_v38
sw $t5,0($t0)
# Original instruction: lw v39,0(v38)
la $t5,label_89_v38
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_91_v39
sw $t4,0($t0)
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

