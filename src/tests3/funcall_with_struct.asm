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
label_62_v25:
.space 4
.align 2
label_14_v1:
.space 4
.align 2
label_30_v9:
.space 4
.align 2
label_85_v35:
.space 4
.align 2
label_73_v31:
.space 4
.align 2
label_63_v27:
.space 4
.align 2
label_83_v28:
.space 4
.align 2
label_52_v22:
.space 4
.align 2
label_54_v21:
.space 4
.align 2
label_33_v12:
.space 4
.align 2
label_82_v34:
.space 4
.align 2
label_23_v7:
.space 4
.align 2
label_31_v11:
.space 4
.align 2
label_55_v23:
.space 4
.align 2
label_70_v30:
.space 4
.align 2
label_44_v18:
.space 4
.align 2
label_60_v26:
.space 4
.align 2
label_46_v17:
.space 4
.align 2
label_15_v3:
.space 4
.align 2
label_28_v10:
.space 4
.align 2
label_67_v29:
.space 4
.align 2
label_76_v32:
.space 4
.align 2
label_49_v20:
.space 4
.align 2
label_39_v15:
.space 4
.align 2
label_22_v5:
.space 4
.align 2
label_12_v2:
.space 4
.align 2
label_20_v6:
.space 4
.align 2
label_41_v16:
.space 4
.align 2
label_38_v13:
.space 4
.align 2
label_17_v4:
.space 4
.align 2
label_47_v19:
.space 4
.align 2
label_79_v33:
.space 4
.align 2
label_9_v0:
.space 4
.align 2
label_57_v24:
.space 4
.align 2
label_36_v14:
.space 4
.align 2
label_25_v8:
.space 4
# Allocated labels for virtual registers
.align 2
label_148_v62:
.space 4
.align 2
label_139_v57:
.space 4
.align 2
label_158_v68:
.space 4
.align 2
label_93_v39:
.space 4
.align 2
label_104_v45:
.space 4
.align 2
label_151_v64:
.space 4
.align 2
label_112_v47:
.space 4
.align 2
label_146_v61:
.space 4
.align 2
label_99_v42:
.space 4
.align 2
label_143_v59:
.space 4
.align 2
label_138_v56:
.space 4
.align 2
label_141_v58:
.space 4
.align 2
label_164_v72:
.space 4
.align 2
label_120_v49:
.space 4
.align 2
label_129_v44:
.space 4
.align 2
label_159_v69:
.space 4
.align 2
label_126_v43:
.space 4
.align 2
label_153_v65:
.space 4
.align 2
label_133_v53:
.space 4
.align 2
label_154_v66:
.space 4
.align 2
label_144_v60:
.space 4
.align 2
label_128_v51:
.space 4
.align 2
label_116_v48:
.space 4
.align 2
label_87_v36:
.space 4
.align 2
label_156_v67:
.space 4
.align 2
label_89_v37:
.space 4
.align 2
label_95_v40:
.space 4
.align 2
label_134_v54:
.space 4
.align 2
label_161_v70:
.space 4
.align 2
label_97_v41:
.space 4
.align 2
label_91_v38:
.space 4
.align 2
label_149_v63:
.space 4
.align 2
label_131_v52:
.space 4
.align 2
label_108_v46:
.space 4
.align 2
label_136_v55:
.space 4
.align 2
label_163_v71:
.space 4
.align 2
label_124_v50:
.space 4

.text
make_pair:
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
# Original instruction: addiu $sp,$sp,-36
addiu $sp,$sp,-36
# Original instruction: addiu v0,$fp,8
addiu $t5,$fp,8
la $t0,label_9_v0
sw $t5,0($t0)
# Original instruction: lw v1,0(v0)
la $t5,label_9_v0
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_14_v1
sw $t4,0($t0)
# Original instruction: addiu v2,$fp,-36
addiu $t5,$fp,-36
la $t0,label_12_v2
sw $t5,0($t0)
# Original instruction: addiu v3,v2,0
la $t5,label_12_v2
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_15_v3
sw $t4,0($t0)
# Original instruction: sw v1,0(v3)
la $t5,label_14_v1
lw $t5,0($t5)
la $t4,label_15_v3
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v4,$fp,12
addiu $t5,$fp,12
la $t0,label_17_v4
sw $t5,0($t0)
# Original instruction: lw v5,0(v4)
la $t5,label_17_v4
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_22_v5
sw $t4,0($t0)
# Original instruction: addiu v6,$fp,-36
addiu $t5,$fp,-36
la $t0,label_20_v6
sw $t5,0($t0)
# Original instruction: addiu v7,v6,4
la $t5,label_20_v6
lw $t5,0($t5)
addiu $t4,$t5,4
la $t0,label_23_v7
sw $t4,0($t0)
# Original instruction: sw v5,0(v7)
la $t5,label_22_v5
lw $t5,0($t5)
la $t4,label_23_v7
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v8,$fp,16
addiu $t5,$fp,16
la $t0,label_25_v8
sw $t5,0($t0)
# Original instruction: lw v9,0(v8)
la $t5,label_25_v8
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_30_v9
sw $t4,0($t0)
# Original instruction: addiu v10,$fp,-36
addiu $t5,$fp,-36
la $t0,label_28_v10
sw $t5,0($t0)
# Original instruction: addiu v11,v10,8
la $t5,label_28_v10
lw $t5,0($t5)
addiu $t4,$t5,8
la $t0,label_31_v11
sw $t4,0($t0)
# Original instruction: sw v9,0(v11)
la $t5,label_30_v9
lw $t5,0($t5)
la $t4,label_31_v11
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v12,$fp,20
addiu $t5,$fp,20
la $t0,label_33_v12
sw $t5,0($t0)
# Original instruction: lw v13,0(v12)
la $t5,label_33_v12
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_38_v13
sw $t4,0($t0)
# Original instruction: addiu v14,$fp,-36
addiu $t5,$fp,-36
la $t0,label_36_v14
sw $t5,0($t0)
# Original instruction: addiu v15,v14,12
la $t5,label_36_v14
lw $t5,0($t5)
addiu $t4,$t5,12
la $t0,label_39_v15
sw $t4,0($t0)
# Original instruction: sw v13,0(v15)
la $t5,label_38_v13
lw $t5,0($t5)
la $t4,label_39_v15
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v16,$fp,24
addiu $t5,$fp,24
la $t0,label_41_v16
sw $t5,0($t0)
# Original instruction: lw v17,0(v16)
la $t5,label_41_v16
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_46_v17
sw $t4,0($t0)
# Original instruction: addiu v18,$fp,-36
addiu $t5,$fp,-36
la $t0,label_44_v18
sw $t5,0($t0)
# Original instruction: addiu v19,v18,16
la $t5,label_44_v18
lw $t5,0($t5)
addiu $t4,$t5,16
la $t0,label_47_v19
sw $t4,0($t0)
# Original instruction: sw v17,0(v19)
la $t5,label_46_v17
lw $t5,0($t5)
la $t4,label_47_v19
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v20,$fp,28
addiu $t5,$fp,28
la $t0,label_49_v20
sw $t5,0($t0)
# Original instruction: lw v21,0(v20)
la $t5,label_49_v20
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_54_v21
sw $t4,0($t0)
# Original instruction: addiu v22,$fp,-36
addiu $t5,$fp,-36
la $t0,label_52_v22
sw $t5,0($t0)
# Original instruction: addiu v23,v22,20
la $t5,label_52_v22
lw $t5,0($t5)
addiu $t4,$t5,20
la $t0,label_55_v23
sw $t4,0($t0)
# Original instruction: sw v21,0(v23)
la $t5,label_54_v21
lw $t5,0($t5)
la $t4,label_55_v23
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v24,$fp,32
addiu $t5,$fp,32
la $t0,label_57_v24
sw $t5,0($t0)
# Original instruction: lw v25,0(v24)
la $t5,label_57_v24
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_62_v25
sw $t4,0($t0)
# Original instruction: addiu v26,$fp,-36
addiu $t5,$fp,-36
la $t0,label_60_v26
sw $t5,0($t0)
# Original instruction: addiu v27,v26,24
la $t5,label_60_v26
lw $t5,0($t5)
addiu $t4,$t5,24
la $t0,label_63_v27
sw $t4,0($t0)
# Original instruction: sw v25,0(v27)
la $t5,label_62_v25
lw $t5,0($t5)
la $t4,label_63_v27
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v28,$fp,-36
addiu $t5,$fp,-36
la $t0,label_83_v28
sw $t5,0($t0)
# Original instruction: lw v29,0(v28)
la $t5,label_83_v28
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_67_v29
sw $t4,0($t0)
# Original instruction: sw v29,0($a0)
la $t5,label_67_v29
lw $t5,0($t5)
sw $t5,0($a0)
# Original instruction: lw v30,4(v28)
la $t5,label_83_v28
lw $t5,0($t5)
lw $t4,4($t5)
la $t0,label_70_v30
sw $t4,0($t0)
# Original instruction: sw v30,4($a0)
la $t5,label_70_v30
lw $t5,0($t5)
sw $t5,4($a0)
# Original instruction: lw v31,8(v28)
la $t5,label_83_v28
lw $t5,0($t5)
lw $t4,8($t5)
la $t0,label_73_v31
sw $t4,0($t0)
# Original instruction: sw v31,8($a0)
la $t5,label_73_v31
lw $t5,0($t5)
sw $t5,8($a0)
# Original instruction: lw v32,12(v28)
la $t5,label_83_v28
lw $t5,0($t5)
lw $t4,12($t5)
la $t0,label_76_v32
sw $t4,0($t0)
# Original instruction: sw v32,12($a0)
la $t5,label_76_v32
lw $t5,0($t5)
sw $t5,12($a0)
# Original instruction: lw v33,16(v28)
la $t5,label_83_v28
lw $t5,0($t5)
lw $t4,16($t5)
la $t0,label_79_v33
sw $t4,0($t0)
# Original instruction: sw v33,16($a0)
la $t5,label_79_v33
lw $t5,0($t5)
sw $t5,16($a0)
# Original instruction: lw v34,20(v28)
la $t5,label_83_v28
lw $t5,0($t5)
lw $t4,20($t5)
la $t0,label_82_v34
sw $t4,0($t0)
# Original instruction: sw v34,20($a0)
la $t5,label_82_v34
lw $t5,0($t5)
sw $t5,20($a0)
# Original instruction: lw v35,24(v28)
la $t5,label_83_v28
lw $t5,0($t5)
lw $t4,24($t5)
la $t0,label_85_v35
sw $t4,0($t0)
# Original instruction: sw v35,24($a0)
la $t5,label_85_v35
lw $t5,0($t5)
sw $t5,24($a0)
# Original instruction: j label_5_return_make_pair
j label_5_return_make_pair
label_5_return_make_pair:
# Original instruction: addiu $sp,$sp,36
addiu $sp,$sp,36
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
# Original instruction: addiu $sp,$sp,-36
addiu $sp,$sp,-36
# Original instruction: addiu $sp,$sp,-28
addiu $sp,$sp,-28
# Original instruction: add $a0,$sp,$zero
add $a0,$sp,$zero
# Original instruction: li v36,9
li $t5,9
la $t0,label_87_v36
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v36,0($sp)
la $t5,label_87_v36
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: li v37,454
li $t5,454
la $t0,label_89_v37
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v37,0($sp)
la $t5,label_89_v37
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: li v38,22
li $t5,22
la $t0,label_91_v38
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v38,0($sp)
la $t5,label_91_v38
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: li v39,3
li $t5,3
la $t0,label_93_v39
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v39,0($sp)
la $t5,label_93_v39
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: li v40,4
li $t5,4
la $t0,label_95_v40
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v40,0($sp)
la $t5,label_95_v40
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: li v41,7
li $t5,7
la $t0,label_97_v41
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v41,0($sp)
la $t5,label_97_v41
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: li v42,5
li $t5,5
la $t0,label_99_v42
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v42,0($sp)
la $t5,label_99_v42
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal make_pair
jal make_pair
# Original instruction: addiu $sp,$sp,56
addiu $sp,$sp,56
# Original instruction: add v43,$a0,$zero
add $t5,$a0,$zero
la $t0,label_126_v43
sw $t5,0($t0)
# Original instruction: addiu v44,$fp,-36
addiu $t5,$fp,-36
la $t0,label_129_v44
sw $t5,0($t0)
# Original instruction: lw v45,0(v43)
la $t5,label_126_v43
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_104_v45
sw $t4,0($t0)
# Original instruction: sw v45,0(v44)
la $t5,label_104_v45
lw $t5,0($t5)
la $t4,label_129_v44
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: lw v46,4(v43)
la $t5,label_126_v43
lw $t5,0($t5)
lw $t4,4($t5)
la $t0,label_108_v46
sw $t4,0($t0)
# Original instruction: sw v46,4(v44)
la $t5,label_108_v46
lw $t5,0($t5)
la $t4,label_129_v44
lw $t4,0($t4)
sw $t5,4($t4)
# Original instruction: lw v47,8(v43)
la $t5,label_126_v43
lw $t5,0($t5)
lw $t4,8($t5)
la $t0,label_112_v47
sw $t4,0($t0)
# Original instruction: sw v47,8(v44)
la $t5,label_112_v47
lw $t5,0($t5)
la $t4,label_129_v44
lw $t4,0($t4)
sw $t5,8($t4)
# Original instruction: lw v48,12(v43)
la $t5,label_126_v43
lw $t5,0($t5)
lw $t4,12($t5)
la $t0,label_116_v48
sw $t4,0($t0)
# Original instruction: sw v48,12(v44)
la $t5,label_116_v48
lw $t5,0($t5)
la $t4,label_129_v44
lw $t4,0($t4)
sw $t5,12($t4)
# Original instruction: lw v49,16(v43)
la $t5,label_126_v43
lw $t5,0($t5)
lw $t4,16($t5)
la $t0,label_120_v49
sw $t4,0($t0)
# Original instruction: sw v49,16(v44)
la $t5,label_120_v49
lw $t5,0($t5)
la $t4,label_129_v44
lw $t4,0($t4)
sw $t5,16($t4)
# Original instruction: lw v50,20(v43)
la $t5,label_126_v43
lw $t5,0($t5)
lw $t4,20($t5)
la $t0,label_124_v50
sw $t4,0($t0)
# Original instruction: sw v50,20(v44)
la $t5,label_124_v50
lw $t5,0($t5)
la $t4,label_129_v44
lw $t4,0($t4)
sw $t5,20($t4)
# Original instruction: lw v51,24(v43)
la $t5,label_126_v43
lw $t5,0($t5)
lw $t4,24($t5)
la $t0,label_128_v51
sw $t4,0($t0)
# Original instruction: sw v51,24(v44)
la $t5,label_128_v51
lw $t5,0($t5)
la $t4,label_129_v44
lw $t4,0($t4)
sw $t5,24($t4)
# Original instruction: addiu v52,$fp,-36
addiu $t5,$fp,-36
la $t0,label_131_v52
sw $t5,0($t0)
# Original instruction: lw v53,0(v52)
la $t5,label_131_v52
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_133_v53
sw $t4,0($t0)
# Original instruction: add $a0,v53,$zero
la $t5,label_133_v53
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v54,$zero,$zero
add $t5,$zero,$zero
la $t0,label_134_v54
sw $t5,0($t0)
# Original instruction: addiu v55,$fp,-36
addiu $t5,$fp,-36
la $t0,label_136_v55
sw $t5,0($t0)
# Original instruction: lw v56,4(v55)
la $t5,label_136_v55
lw $t5,0($t5)
lw $t4,4($t5)
la $t0,label_138_v56
sw $t4,0($t0)
# Original instruction: add $a0,v56,$zero
la $t5,label_138_v56
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v57,$zero,$zero
add $t5,$zero,$zero
la $t0,label_139_v57
sw $t5,0($t0)
# Original instruction: addiu v58,$fp,-36
addiu $t5,$fp,-36
la $t0,label_141_v58
sw $t5,0($t0)
# Original instruction: lw v59,8(v58)
la $t5,label_141_v58
lw $t5,0($t5)
lw $t4,8($t5)
la $t0,label_143_v59
sw $t4,0($t0)
# Original instruction: add $a0,v59,$zero
la $t5,label_143_v59
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v60,$zero,$zero
add $t5,$zero,$zero
la $t0,label_144_v60
sw $t5,0($t0)
# Original instruction: addiu v61,$fp,-36
addiu $t5,$fp,-36
la $t0,label_146_v61
sw $t5,0($t0)
# Original instruction: lw v62,12(v61)
la $t5,label_146_v61
lw $t5,0($t5)
lw $t4,12($t5)
la $t0,label_148_v62
sw $t4,0($t0)
# Original instruction: add $a0,v62,$zero
la $t5,label_148_v62
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v63,$zero,$zero
add $t5,$zero,$zero
la $t0,label_149_v63
sw $t5,0($t0)
# Original instruction: addiu v64,$fp,-36
addiu $t5,$fp,-36
la $t0,label_151_v64
sw $t5,0($t0)
# Original instruction: lw v65,16(v64)
la $t5,label_151_v64
lw $t5,0($t5)
lw $t4,16($t5)
la $t0,label_153_v65
sw $t4,0($t0)
# Original instruction: add $a0,v65,$zero
la $t5,label_153_v65
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v66,$zero,$zero
add $t5,$zero,$zero
la $t0,label_154_v66
sw $t5,0($t0)
# Original instruction: addiu v67,$fp,-36
addiu $t5,$fp,-36
la $t0,label_156_v67
sw $t5,0($t0)
# Original instruction: lw v68,20(v67)
la $t5,label_156_v67
lw $t5,0($t5)
lw $t4,20($t5)
la $t0,label_158_v68
sw $t4,0($t0)
# Original instruction: add $a0,v68,$zero
la $t5,label_158_v68
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v69,$zero,$zero
add $t5,$zero,$zero
la $t0,label_159_v69
sw $t5,0($t0)
# Original instruction: addiu v70,$fp,-36
addiu $t5,$fp,-36
la $t0,label_161_v70
sw $t5,0($t0)
# Original instruction: lw v71,24(v70)
la $t5,label_161_v70
lw $t5,0($t5)
lw $t4,24($t5)
la $t0,label_163_v71
sw $t4,0($t0)
# Original instruction: add $a0,v71,$zero
la $t5,label_163_v71
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v72,$zero,$zero
add $t5,$zero,$zero
la $t0,label_164_v72
sw $t5,0($t0)
label_7_return_main:
# Original instruction: addiu $sp,$sp,36
addiu $sp,$sp,36
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

