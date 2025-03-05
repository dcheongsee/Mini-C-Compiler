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
label_42_v15:
.space 4
.align 2
label_58_v25:
.space 4
.align 2
label_65_v28:
.space 4
.align 2
label_63_v27:
.space 4
.align 2
label_14_v1:
.space 4
.align 2
label_21_v6:
.space 4
.align 2
label_28_v8:
.space 4
.align 2
label_20_v4:
.space 4
.align 2
label_46_v19:
.space 4
.align 2
label_73_v29:
.space 4
.align 2
label_35_v13:
.space 4
.align 2
label_72_v31:
.space 4
.align 2
label_34_v11:
.space 4
.align 2
label_55_v23:
.space 4
.align 2
label_9_v0:
.space 4
.align 2
label_43_v17:
.space 4
.align 2
label_48_v18:
.space 4
.align 2
label_60_v24:
.space 4
.align 2
label_26_v9:
.space 4
.align 2
label_29_v10:
.space 4
.align 2
label_54_v21:
.space 4
.align 2
label_12_v2:
.space 4
.align 2
label_15_v3:
.space 4
.align 2
label_32_v12:
.space 4
.align 2
label_40_v16:
.space 4
.align 2
label_75_v32:
.space 4
.align 2
label_23_v7:
.space 4
.align 2
label_69_v30:
.space 4
.align 2
label_49_v20:
.space 4
.align 2
label_52_v22:
.space 4
.align 2
label_61_v26:
.space 4
.align 2
label_37_v14:
.space 4
.align 2
label_18_v5:
.space 4
# Allocated labels for virtual registers
.align 2
label_176_v81:
.space 4
.align 2
label_152_v69:
.space 4
.align 2
label_157_v71:
.space 4
.align 2
label_102_v45:
.space 4
.align 2
label_129_v59:
.space 4
.align 2
label_82_v35:
.space 4
.align 2
label_91_v40:
.space 4
.align 2
label_160_v73:
.space 4
.align 2
label_106_v47:
.space 4
.align 2
label_111_v50:
.space 4
.align 2
label_144_v67:
.space 4
.align 2
label_117_v53:
.space 4
.align 2
label_165_v75:
.space 4
.align 2
label_87_v38:
.space 4
.align 2
label_103_v46:
.space 4
.align 2
label_170_v78:
.space 4
.align 2
label_150_v65:
.space 4
.align 2
label_79_v34:
.space 4
.align 2
label_107_v48:
.space 4
.align 2
label_90_v39:
.space 4
.align 2
label_121_v55:
.space 4
.align 2
label_127_v58:
.space 4
.align 2
label_123_v56:
.space 4
.align 2
label_162_v74:
.space 4
.align 2
label_115_v52:
.space 4
.align 2
label_137_v63:
.space 4
.align 2
label_153_v66:
.space 4
.align 2
label_125_v57:
.space 4
.align 2
label_175_v80:
.space 4
.align 2
label_98_v43:
.space 4
.align 2
label_148_v68:
.space 4
.align 2
label_168_v77:
.space 4
.align 2
label_109_v49:
.space 4
.align 2
label_164_v72:
.space 4
.align 2
label_86_v37:
.space 4
.align 2
label_131_v60:
.space 4
.align 2
label_94_v41:
.space 4
.align 2
label_78_v33:
.space 4
.align 2
label_133_v61:
.space 4
.align 2
label_83_v36:
.space 4
.align 2
label_139_v64:
.space 4
.align 2
label_99_v44:
.space 4
.align 2
label_113_v51:
.space 4
.align 2
label_119_v54:
.space 4
.align 2
label_135_v62:
.space 4
.align 2
label_172_v76:
.space 4
.align 2
label_155_v70:
.space 4
.align 2
label_173_v79:
.space 4
.align 2
label_95_v42:
.space 4

.text
f:
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
la $t0,label_9_v0
sw $t5,0($t0)
# Original instruction: lw v1,0(v0)
la $t5,label_9_v0
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_14_v1
sw $t4,0($t0)
# Original instruction: addiu v2,$fp,12
addiu $t5,$fp,12
la $t0,label_12_v2
sw $t5,0($t0)
# Original instruction: lw v3,0(v2)
la $t5,label_12_v2
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_15_v3
sw $t4,0($t0)
# Original instruction: add v4,v1,v3
la $t5,label_14_v1
lw $t5,0($t5)
la $t4,label_15_v3
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_20_v4
sw $t3,0($t0)
# Original instruction: addiu v5,$fp,-20
addiu $t5,$fp,-20
la $t0,label_18_v5
sw $t5,0($t0)
# Original instruction: addiu v6,v5,0
la $t5,label_18_v5
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_21_v6
sw $t4,0($t0)
# Original instruction: sw v4,0(v6)
la $t5,label_20_v4
lw $t5,0($t5)
la $t4,label_21_v6
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v7,$fp,16
addiu $t5,$fp,16
la $t0,label_23_v7
sw $t5,0($t0)
# Original instruction: lw v8,0(v7)
la $t5,label_23_v7
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_28_v8
sw $t4,0($t0)
# Original instruction: addiu v9,$fp,20
addiu $t5,$fp,20
la $t0,label_26_v9
sw $t5,0($t0)
# Original instruction: lw v10,0(v9)
la $t5,label_26_v9
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_29_v10
sw $t4,0($t0)
# Original instruction: add v11,v8,v10
la $t5,label_28_v8
lw $t5,0($t5)
la $t4,label_29_v10
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_34_v11
sw $t3,0($t0)
# Original instruction: addiu v12,$fp,-20
addiu $t5,$fp,-20
la $t0,label_32_v12
sw $t5,0($t0)
# Original instruction: addiu v13,v12,4
la $t5,label_32_v12
lw $t5,0($t5)
addiu $t4,$t5,4
la $t0,label_35_v13
sw $t4,0($t0)
# Original instruction: sw v11,0(v13)
la $t5,label_34_v11
lw $t5,0($t5)
la $t4,label_35_v13
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v14,$fp,24
addiu $t5,$fp,24
la $t0,label_37_v14
sw $t5,0($t0)
# Original instruction: lw v15,0(v14)
la $t5,label_37_v14
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_42_v15
sw $t4,0($t0)
# Original instruction: addiu v16,$fp,28
addiu $t5,$fp,28
la $t0,label_40_v16
sw $t5,0($t0)
# Original instruction: lw v17,0(v16)
la $t5,label_40_v16
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_43_v17
sw $t4,0($t0)
# Original instruction: add v18,v15,v17
la $t5,label_42_v15
lw $t5,0($t5)
la $t4,label_43_v17
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_48_v18
sw $t3,0($t0)
# Original instruction: addiu v19,$fp,32
addiu $t5,$fp,32
la $t0,label_46_v19
sw $t5,0($t0)
# Original instruction: lw v20,0(v19)
la $t5,label_46_v19
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_49_v20
sw $t4,0($t0)
# Original instruction: add v21,v18,v20
la $t5,label_48_v18
lw $t5,0($t5)
la $t4,label_49_v20
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_54_v21
sw $t3,0($t0)
# Original instruction: addiu v22,$fp,36
addiu $t5,$fp,36
la $t0,label_52_v22
sw $t5,0($t0)
# Original instruction: lw v23,0(v22)
la $t5,label_52_v22
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_55_v23
sw $t4,0($t0)
# Original instruction: add v24,v21,v23
la $t5,label_54_v21
lw $t5,0($t5)
la $t4,label_55_v23
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_60_v24
sw $t3,0($t0)
# Original instruction: addiu v25,$fp,-20
addiu $t5,$fp,-20
la $t0,label_58_v25
sw $t5,0($t0)
# Original instruction: addiu v26,v25,8
la $t5,label_58_v25
lw $t5,0($t5)
addiu $t4,$t5,8
la $t0,label_61_v26
sw $t4,0($t0)
# Original instruction: sw v24,0(v26)
la $t5,label_60_v24
lw $t5,0($t5)
la $t4,label_61_v26
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v27,$fp,-20
addiu $t5,$fp,-20
la $t0,label_63_v27
sw $t5,0($t0)
# Original instruction: lw v28,0(v27)
la $t5,label_63_v27
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_65_v28
sw $t4,0($t0)
# Original instruction: add $v0,v28,$zero
la $t5,label_65_v28
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_f
j label_5_return_f
label_5_return_f:
# Original instruction: addiu v29,$fp,-20
addiu $t5,$fp,-20
la $t0,label_73_v29
sw $t5,0($t0)
# Original instruction: lw v30,0(v29)
la $t5,label_73_v29
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_69_v30
sw $t4,0($t0)
# Original instruction: sw v30,0($a0)
la $t5,label_69_v30
lw $t5,0($t5)
sw $t5,0($a0)
# Original instruction: lw v31,4(v29)
la $t5,label_73_v29
lw $t5,0($t5)
lw $t4,4($t5)
la $t0,label_72_v31
sw $t4,0($t0)
# Original instruction: sw v31,4($a0)
la $t5,label_72_v31
lw $t5,0($t5)
sw $t5,4($a0)
# Original instruction: lw v32,8(v29)
la $t5,label_73_v29
lw $t5,0($t5)
lw $t4,8($t5)
la $t0,label_75_v32
sw $t4,0($t0)
# Original instruction: sw v32,8($a0)
la $t5,label_75_v32
lw $t5,0($t5)
sw $t5,8($a0)
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
# Original instruction: addiu $sp,$sp,-8
addiu $sp,$sp,-8
# Original instruction: sw $fp,4($sp)
sw $fp,4($sp)
# Original instruction: sw $ra,0($sp)
sw $ra,0($sp)
# Original instruction: add $fp,$sp,$zero
add $fp,$sp,$zero
# Original instruction: addiu $sp,$sp,-52
addiu $sp,$sp,-52
# Original instruction: li v33,1
li $t5,1
la $t0,label_78_v33
sw $t5,0($t0)
# Original instruction: addiu v34,$fp,-24
addiu $t5,$fp,-24
la $t0,label_79_v34
sw $t5,0($t0)
# Original instruction: sw v33,0(v34)
la $t5,label_78_v33
lw $t5,0($t5)
la $t4,label_79_v34
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v35,2
li $t5,2
la $t0,label_82_v35
sw $t5,0($t0)
# Original instruction: addiu v36,$fp,-28
addiu $t5,$fp,-28
la $t0,label_83_v36
sw $t5,0($t0)
# Original instruction: sw v35,0(v36)
la $t5,label_82_v35
lw $t5,0($t5)
la $t4,label_83_v36
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v37,3
li $t5,3
la $t0,label_86_v37
sw $t5,0($t0)
# Original instruction: addiu v38,$fp,-32
addiu $t5,$fp,-32
la $t0,label_87_v38
sw $t5,0($t0)
# Original instruction: sw v37,0(v38)
la $t5,label_86_v37
lw $t5,0($t5)
la $t4,label_87_v38
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v39,4
li $t5,4
la $t0,label_90_v39
sw $t5,0($t0)
# Original instruction: addiu v40,$fp,-36
addiu $t5,$fp,-36
la $t0,label_91_v40
sw $t5,0($t0)
# Original instruction: sw v39,0(v40)
la $t5,label_90_v39
lw $t5,0($t5)
la $t4,label_91_v40
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v41,5
li $t5,5
la $t0,label_94_v41
sw $t5,0($t0)
# Original instruction: addiu v42,$fp,-40
addiu $t5,$fp,-40
la $t0,label_95_v42
sw $t5,0($t0)
# Original instruction: sw v41,0(v42)
la $t5,label_94_v41
lw $t5,0($t5)
la $t4,label_95_v42
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v43,6
li $t5,6
la $t0,label_98_v43
sw $t5,0($t0)
# Original instruction: addiu v44,$fp,-44
addiu $t5,$fp,-44
la $t0,label_99_v44
sw $t5,0($t0)
# Original instruction: sw v43,0(v44)
la $t5,label_98_v43
lw $t5,0($t5)
la $t4,label_99_v44
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v45,7
li $t5,7
la $t0,label_102_v45
sw $t5,0($t0)
# Original instruction: addiu v46,$fp,-48
addiu $t5,$fp,-48
la $t0,label_103_v46
sw $t5,0($t0)
# Original instruction: sw v45,0(v46)
la $t5,label_102_v45
lw $t5,0($t5)
la $t4,label_103_v46
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v47,8
li $t5,8
la $t0,label_106_v47
sw $t5,0($t0)
# Original instruction: addiu v48,$fp,-52
addiu $t5,$fp,-52
la $t0,label_107_v48
sw $t5,0($t0)
# Original instruction: sw v47,0(v48)
la $t5,label_106_v47
lw $t5,0($t5)
la $t4,label_107_v48
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu $sp,$sp,-12
addiu $sp,$sp,-12
# Original instruction: add $a0,$sp,$zero
add $a0,$sp,$zero
# Original instruction: addiu v49,$fp,-52
addiu $t5,$fp,-52
la $t0,label_109_v49
sw $t5,0($t0)
# Original instruction: lw v50,0(v49)
la $t5,label_109_v49
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_111_v50
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v50,0($sp)
la $t5,label_111_v50
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v51,$fp,-48
addiu $t5,$fp,-48
la $t0,label_113_v51
sw $t5,0($t0)
# Original instruction: lw v52,0(v51)
la $t5,label_113_v51
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_115_v52
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v52,0($sp)
la $t5,label_115_v52
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v53,$fp,-44
addiu $t5,$fp,-44
la $t0,label_117_v53
sw $t5,0($t0)
# Original instruction: lw v54,0(v53)
la $t5,label_117_v53
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_119_v54
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v54,0($sp)
la $t5,label_119_v54
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v55,$fp,-40
addiu $t5,$fp,-40
la $t0,label_121_v55
sw $t5,0($t0)
# Original instruction: lw v56,0(v55)
la $t5,label_121_v55
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_123_v56
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v56,0($sp)
la $t5,label_123_v56
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v57,$fp,-36
addiu $t5,$fp,-36
la $t0,label_125_v57
sw $t5,0($t0)
# Original instruction: lw v58,0(v57)
la $t5,label_125_v57
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_127_v58
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v58,0($sp)
la $t5,label_127_v58
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v59,$fp,-32
addiu $t5,$fp,-32
la $t0,label_129_v59
sw $t5,0($t0)
# Original instruction: lw v60,0(v59)
la $t5,label_129_v59
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_131_v60
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v60,0($sp)
la $t5,label_131_v60
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v61,$fp,-28
addiu $t5,$fp,-28
la $t0,label_133_v61
sw $t5,0($t0)
# Original instruction: lw v62,0(v61)
la $t5,label_133_v61
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_135_v62
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v62,0($sp)
la $t5,label_135_v62
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v63,$fp,-24
addiu $t5,$fp,-24
la $t0,label_137_v63
sw $t5,0($t0)
# Original instruction: lw v64,0(v63)
la $t5,label_137_v63
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_139_v64
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v64,0($sp)
la $t5,label_139_v64
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal f
jal f
# Original instruction: addiu $sp,$sp,36
addiu $sp,$sp,36
# Original instruction: add v65,$a0,$zero
add $t5,$a0,$zero
la $t0,label_150_v65
sw $t5,0($t0)
# Original instruction: addiu v66,$fp,-20
addiu $t5,$fp,-20
la $t0,label_153_v66
sw $t5,0($t0)
# Original instruction: lw v67,0(v65)
la $t5,label_150_v65
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_144_v67
sw $t4,0($t0)
# Original instruction: sw v67,0(v66)
la $t5,label_144_v67
lw $t5,0($t5)
la $t4,label_153_v66
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: lw v68,4(v65)
la $t5,label_150_v65
lw $t5,0($t5)
lw $t4,4($t5)
la $t0,label_148_v68
sw $t4,0($t0)
# Original instruction: sw v68,4(v66)
la $t5,label_148_v68
lw $t5,0($t5)
la $t4,label_153_v66
lw $t4,0($t4)
sw $t5,4($t4)
# Original instruction: lw v69,8(v65)
la $t5,label_150_v65
lw $t5,0($t5)
lw $t4,8($t5)
la $t0,label_152_v69
sw $t4,0($t0)
# Original instruction: sw v69,8(v66)
la $t5,label_152_v69
lw $t5,0($t5)
la $t4,label_153_v66
lw $t4,0($t4)
sw $t5,8($t4)
# Original instruction: addiu v70,$fp,-20
addiu $t5,$fp,-20
la $t0,label_155_v70
sw $t5,0($t0)
# Original instruction: addiu v71,v70,0
la $t5,label_155_v70
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_157_v71
sw $t4,0($t0)
# Original instruction: lw v72,0(v71)
la $t5,label_157_v71
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_164_v72
sw $t4,0($t0)
# Original instruction: addiu v73,$fp,-20
addiu $t5,$fp,-20
la $t0,label_160_v73
sw $t5,0($t0)
# Original instruction: addiu v74,v73,4
la $t5,label_160_v73
lw $t5,0($t5)
addiu $t4,$t5,4
la $t0,label_162_v74
sw $t4,0($t0)
# Original instruction: lw v75,0(v74)
la $t5,label_162_v74
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_165_v75
sw $t4,0($t0)
# Original instruction: add v76,v72,v75
la $t5,label_164_v72
lw $t5,0($t5)
la $t4,label_165_v75
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_172_v76
sw $t3,0($t0)
# Original instruction: addiu v77,$fp,-20
addiu $t5,$fp,-20
la $t0,label_168_v77
sw $t5,0($t0)
# Original instruction: addiu v78,v77,8
la $t5,label_168_v77
lw $t5,0($t5)
addiu $t4,$t5,8
la $t0,label_170_v78
sw $t4,0($t0)
# Original instruction: lw v79,0(v78)
la $t5,label_170_v78
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_173_v79
sw $t4,0($t0)
# Original instruction: add v80,v76,v79
la $t5,label_172_v76
lw $t5,0($t5)
la $t4,label_173_v79
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_175_v80
sw $t3,0($t0)
# Original instruction: add $a0,v80,$zero
la $t5,label_175_v80
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v81,$zero,$zero
add $t5,$zero,$zero
la $t0,label_176_v81
sw $t5,0($t0)
label_7_return_main:
# Original instruction: addiu $sp,$sp,52
addiu $sp,$sp,52
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

