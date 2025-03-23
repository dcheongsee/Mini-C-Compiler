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
str_2532978532:
.asciiz "Entering recurse: d.x = "
str_1292545001:
.asciiz ", n = "
str_10:
.asciiz "\n"
str_1862757759:
.asciiz "Final d.x = "
# Allocated labels for virtual registers
.align 2
label_25_v4:
.space 4
.align 2
label_22_v2:
.space 4
.align 2
label_33_v8:
.space 4
.align 2
label_31_v7:
.space 4
.align 2
label_30_v5:
.space 4
.align 2
label_35_v9:
.space 4
.align 2
label_19_v1:
.space 4
.align 2
label_24_v3:
.space 4
.align 2
label_17_v0:
.space 4
.align 2
label_28_v6:
.space 4
# Allocated labels for virtual registers
.align 2
label_53_v20:
.space 4
.align 2
label_74_v31:
.space 4
.align 2
label_86_v37:
.space 4
.align 2
label_90_v39:
.space 4
.align 2
label_81_v34:
.space 4
.align 2
label_64_v25:
.space 4
.align 2
label_42_v13:
.space 4
.align 2
label_66_v28:
.space 4
.align 2
label_38_v11:
.space 4
.align 2
label_84_v36:
.space 4
.align 2
label_45_v15:
.space 4
.align 2
label_50_v18:
.space 4
.align 2
label_82_v35:
.space 4
.align 2
label_54_v21:
.space 4
.align 2
label_48_v17:
.space 4
.align 2
label_92_v40:
.space 4
.align 2
label_68_v27:
.space 4
.align 2
label_43_v14:
.space 4
.align 2
label_70_v29:
.space 4
.align 2
label_76_v32:
.space 4
.align 2
label_40_v12:
.space 4
.align 2
label_79_v33:
.space 4
.align 2
label_51_v19:
.space 4
.align 2
label_46_v16:
.space 4
.align 2
label_56_v22:
.space 4
.align 2
label_58_v23:
.space 4
.align 2
label_63_v26:
.space 4
.align 2
label_61_v24:
.space 4
.align 2
label_72_v30:
.space 4
.align 2
label_88_v38:
.space 4
.align 2
label_37_v10:
.space 4
# Allocated labels for virtual registers
.align 2
label_95_v42:
.space 4
.align 2
label_110_v48:
.space 4
.align 2
label_100_v44:
.space 4
.align 2
label_97_v41:
.space 4
.align 2
label_107_v47:
.space 4
.align 2
label_118_v54:
.space 4
.align 2
label_112_v50:
.space 4
.align 2
label_115_v52:
.space 4
.align 2
label_109_v49:
.space 4
.align 2
label_98_v43:
.space 4
.align 2
label_102_v45:
.space 4
.align 2
label_117_v53:
.space 4
.align 2
label_104_v46:
.space 4
.align 2
label_113_v51:
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
la $t0,label_17_v0
sw $t5,0($t0)
# Original instruction: lw v1,0(v0)
la $t5,label_17_v0
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_19_v1
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v1,0($sp)
la $t5,label_19_v1
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: li v2,1
li $t5,1
la $t0,label_22_v2
sw $t5,0($t0)
# Original instruction: lw v3,0($sp)
lw $t5,0($sp)
la $t0,label_24_v3
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v4,v2,$zero
la $t5,label_22_v2
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_25_v4
sw $t4,0($t0)
# Original instruction: add v5,v3,v4
la $t5,label_24_v3
lw $t5,0($t5)
la $t4,label_25_v4
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_30_v5
sw $t3,0($t0)
# Original instruction: addiu v6,$fp,8
addiu $t5,$fp,8
la $t0,label_28_v6
sw $t5,0($t0)
# Original instruction: addiu v7,v6,0
la $t5,label_28_v6
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_31_v7
sw $t4,0($t0)
# Original instruction: sw v5,0(v7)
la $t5,label_30_v5
lw $t5,0($t5)
la $t4,label_31_v7
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v8,$fp,8
addiu $t5,$fp,8
la $t0,label_33_v8
sw $t5,0($t0)
# Original instruction: lw v9,0(v8)
la $t5,label_33_v8
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_35_v9
sw $t4,0($t0)
# Original instruction: sw v9,0($a0)
la $t5,label_35_v9
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
# Original instruction: la v10,str_2532978532
la $t5,str_2532978532
la $t0,label_37_v10
sw $t5,0($t0)
# Original instruction: add $a0,v10,$zero
la $t5,label_37_v10
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v11,$zero,$zero
add $t5,$zero,$zero
la $t0,label_38_v11
sw $t5,0($t0)
# Original instruction: addiu v12,$fp,8
addiu $t5,$fp,8
la $t0,label_40_v12
sw $t5,0($t0)
# Original instruction: lw v13,0(v12)
la $t5,label_40_v12
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_42_v13
sw $t4,0($t0)
# Original instruction: add $a0,v13,$zero
la $t5,label_42_v13
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v14,$zero,$zero
add $t5,$zero,$zero
la $t0,label_43_v14
sw $t5,0($t0)
# Original instruction: la v15,str_1292545001
la $t5,str_1292545001
la $t0,label_45_v15
sw $t5,0($t0)
# Original instruction: add $a0,v15,$zero
la $t5,label_45_v15
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v16,$zero,$zero
add $t5,$zero,$zero
la $t0,label_46_v16
sw $t5,0($t0)
# Original instruction: addiu v17,$fp,12
addiu $t5,$fp,12
la $t0,label_48_v17
sw $t5,0($t0)
# Original instruction: lw v18,0(v17)
la $t5,label_48_v17
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_50_v18
sw $t4,0($t0)
# Original instruction: add $a0,v18,$zero
la $t5,label_50_v18
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v19,$zero,$zero
add $t5,$zero,$zero
la $t0,label_51_v19
sw $t5,0($t0)
# Original instruction: la v20,str_10
la $t5,str_10
la $t0,label_53_v20
sw $t5,0($t0)
# Original instruction: add $a0,v20,$zero
la $t5,label_53_v20
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v21,$zero,$zero
add $t5,$zero,$zero
la $t0,label_54_v21
sw $t5,0($t0)
# Original instruction: addiu v22,$fp,12
addiu $t5,$fp,12
la $t0,label_56_v22
sw $t5,0($t0)
# Original instruction: lw v23,0(v22)
la $t5,label_56_v22
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_58_v23
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v23,0($sp)
la $t5,label_58_v23
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: li v24,0
li $t5,0
la $t0,label_61_v24
sw $t5,0($t0)
# Original instruction: lw v25,0($sp)
lw $t5,0($sp)
la $t0,label_64_v25
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v26,v24,$zero
la $t5,label_61_v24
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_63_v26
sw $t4,0($t0)
# Original instruction: slt v28,v26,v25
la $t5,label_63_v26
lw $t5,0($t5)
la $t4,label_64_v25
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_66_v28
sw $t3,0($t0)
# Original instruction: xori v27,v28,1
la $t5,label_66_v28
lw $t5,0($t5)
xori $t4,$t5,1
la $t0,label_68_v27
sw $t4,0($t0)
# Original instruction: beqz v27,label_11_if_else
la $t5,label_68_v27
lw $t5,0($t5)
beqz $t5,label_11_if_else
# Original instruction: addiu v29,$fp,8
addiu $t5,$fp,8
la $t0,label_70_v29
sw $t5,0($t0)
# Original instruction: lw v30,0(v29)
la $t5,label_70_v29
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_72_v30
sw $t4,0($t0)
# Original instruction: sw v30,0($a0)
la $t5,label_72_v30
lw $t5,0($t5)
sw $t5,0($a0)
# Original instruction: j label_7_return_recurse
j label_7_return_recurse
# Original instruction: j label_12_if_end
j label_12_if_end
label_11_if_else:
label_12_if_end:
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: add $a0,$sp,$zero
add $a0,$sp,$zero
# Original instruction: addiu v31,$fp,12
addiu $t5,$fp,12
la $t0,label_74_v31
sw $t5,0($t0)
# Original instruction: lw v32,0(v31)
la $t5,label_74_v31
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_76_v32
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v32,0($sp)
la $t5,label_76_v32
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: li v33,1
li $t5,1
la $t0,label_79_v33
sw $t5,0($t0)
# Original instruction: lw v34,0($sp)
lw $t5,0($sp)
la $t0,label_81_v34
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v35,v33,$zero
la $t5,label_79_v33
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_82_v35
sw $t4,0($t0)
# Original instruction: sub v36,v34,v35
la $t5,label_81_v34
lw $t5,0($t5)
la $t4,label_82_v35
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_84_v36
sw $t3,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v36,0($sp)
la $t5,label_84_v36
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: add $a0,$sp,$zero
add $a0,$sp,$zero
# Original instruction: addiu v37,$fp,8
addiu $t5,$fp,8
la $t0,label_86_v37
sw $t5,0($t0)
# Original instruction: lw v38,0(v37)
la $t5,label_86_v37
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_88_v38
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v38,0($sp)
la $t5,label_88_v38
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal addOne
jal addOne
# Original instruction: add v39,$a0,$zero
add $t5,$a0,$zero
la $t0,label_90_v39
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v39,0($sp)
la $t5,label_90_v39
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal recurse
jal recurse
# Original instruction: add v40,$a0,$zero
add $t5,$a0,$zero
la $t0,label_92_v40
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: add $v0,v40,$zero
la $t5,label_92_v40
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
# Original instruction: li v41,1
li $t5,1
la $t0,label_97_v41
sw $t5,0($t0)
# Original instruction: addiu v42,$fp,-12
addiu $t5,$fp,-12
la $t0,label_95_v42
sw $t5,0($t0)
# Original instruction: addiu v43,v42,0
la $t5,label_95_v42
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_98_v43
sw $t4,0($t0)
# Original instruction: sw v41,0(v43)
la $t5,label_97_v41
lw $t5,0($t5)
la $t4,label_98_v43
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: add $a0,$sp,$zero
add $a0,$sp,$zero
# Original instruction: li v44,3
li $t5,3
la $t0,label_100_v44
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v44,0($sp)
la $t5,label_100_v44
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v45,$fp,-12
addiu $t5,$fp,-12
la $t0,label_102_v45
sw $t5,0($t0)
# Original instruction: lw v46,0(v45)
la $t5,label_102_v45
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_104_v46
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v46,0($sp)
la $t5,label_104_v46
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal recurse
jal recurse
# Original instruction: add v47,$a0,$zero
add $t5,$a0,$zero
la $t0,label_107_v47
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: addiu v48,$fp,-12
addiu $t5,$fp,-12
la $t0,label_110_v48
sw $t5,0($t0)
# Original instruction: lw v49,0(v47)
la $t5,label_107_v47
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_109_v49
sw $t4,0($t0)
# Original instruction: sw v49,0(v48)
la $t5,label_109_v49
lw $t5,0($t5)
la $t4,label_110_v48
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: la v50,str_1862757759
la $t5,str_1862757759
la $t0,label_112_v50
sw $t5,0($t0)
# Original instruction: add $a0,v50,$zero
la $t5,label_112_v50
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v51,$zero,$zero
add $t5,$zero,$zero
la $t0,label_113_v51
sw $t5,0($t0)
# Original instruction: addiu v52,$fp,-12
addiu $t5,$fp,-12
la $t0,label_115_v52
sw $t5,0($t0)
# Original instruction: lw v53,0(v52)
la $t5,label_115_v52
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_117_v53
sw $t4,0($t0)
# Original instruction: add $a0,v53,$zero
la $t5,label_117_v53
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v54,$zero,$zero
add $t5,$zero,$zero
la $t0,label_118_v54
sw $t5,0($t0)
label_14_return_main:
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

