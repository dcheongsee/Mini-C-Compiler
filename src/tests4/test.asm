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
label_95_v43:
.space 4
.align 2
label_14_v2:
.space 4
.align 2
label_138_v64:
.space 4
.align 2
label_11_v1:
.space 4
.align 2
label_163_v77:
.space 4
.align 2
label_179_v85:
.space 4
.align 2
label_79_v35:
.space 4
.align 2
label_69_v30:
.space 4
.align 2
label_107_v49:
.space 4
.align 2
label_85_v38:
.space 4
.align 2
label_193_v92:
.space 4
.align 2
label_31_v11:
.space 4
.align 2
label_74_v32:
.space 4
.align 2
label_123_v57:
.space 4
.align 2
label_161_v76:
.space 4
.align 2
label_115_v53:
.space 4
.align 2
label_176_v83:
.space 4
.align 2
label_191_v91:
.space 4
.align 2
label_44_v17:
.space 4
.align 2
label_65_v28:
.space 4
.align 2
label_59_v25:
.space 4
.align 2
label_168_v79:
.space 4
.align 2
label_196_v93:
.space 4
.align 2
label_178_v84:
.space 4
.align 2
label_209_v100:
.space 4
.align 2
label_212_v101:
.space 4
.align 2
label_145_v68:
.space 4
.align 2
label_104_v47:
.space 4
.align 2
label_88_v39:
.space 4
.align 2
label_19_v5:
.space 4
.align 2
label_78_v34:
.space 4
.align 2
label_58_v24:
.space 4
.align 2
label_101_v46:
.space 4
.align 2
label_30_v10:
.space 4
.align 2
label_56_v23:
.space 4
.align 2
label_120_v55:
.space 4
.align 2
label_113_v52:
.space 4
.align 2
label_39_v15:
.space 4
.align 2
label_198_v94:
.space 4
.align 2
label_218_v105:
.space 4
.align 2
label_110_v50:
.space 4
.align 2
label_181_v86:
.space 4
.align 2
label_131_v61:
.space 4
.align 2
label_157_v74:
.space 4
.align 2
label_220_v106:
.space 4
.align 2
label_171_v81:
.space 4
.align 2
label_45_v18:
.space 4
.align 2
label_188_v89:
.space 4
.align 2
label_201_v96:
.space 4
.align 2
label_208_v99:
.space 4
.align 2
label_99_v45:
.space 4
.align 2
label_34_v12:
.space 4
.align 2
label_136_v63:
.space 4
.align 2
label_183_v87:
.space 4
.align 2
label_83_v37:
.space 4
.align 2
label_35_v13:
.space 4
.align 2
label_26_v8:
.space 4
.align 2
label_133_v62:
.space 4
.align 2
label_189_v90:
.space 4
.align 2
label_217_v104:
.space 4
.align 2
label_153_v72:
.space 4
.align 2
label_169_v80:
.space 4
.align 2
label_47_v19:
.space 4
.align 2
label_150_v70:
.space 4
.align 2
label_111_v51:
.space 4
.align 2
label_199_v95:
.space 4
.align 2
label_27_v9:
.space 4
.align 2
label_206_v98:
.space 4
.align 2
label_117_v54:
.space 4
.align 2
label_91_v41:
.space 4
.align 2
label_97_v44:
.space 4
.align 2
label_63_v27:
.space 4
.align 2
label_37_v14:
.space 4
.align 2
label_106_v48:
.space 4
.align 2
label_147_v69:
.space 4
.align 2
label_215_v103:
.space 4
.align 2
label_129_v60:
.space 4
.align 2
label_62_v26:
.space 4
.align 2
label_49_v20:
.space 4
.align 2
label_156_v73:
.space 4
.align 2
label_51_v21:
.space 4
.align 2
label_203_v97:
.space 4
.align 2
label_18_v4:
.space 4
.align 2
label_23_v7:
.space 4
.align 2
label_90_v40:
.space 4
.align 2
label_126_v58:
.space 4
.align 2
label_213_v102:
.space 4
.align 2
label_75_v33:
.space 4
.align 2
label_94_v42:
.space 4
.align 2
label_152_v71:
.space 4
.align 2
label_186_v88:
.space 4
.align 2
label_22_v6:
.space 4
.align 2
label_67_v29:
.space 4
.align 2
label_53_v22:
.space 4
.align 2
label_142_v66:
.space 4
.align 2
label_173_v82:
.space 4
.align 2
label_127_v59:
.space 4
.align 2
label_166_v78:
.space 4
.align 2
label_159_v75:
.space 4
.align 2
label_139_v65:
.space 4
.align 2
label_143_v67:
.space 4
.align 2
label_122_v56:
.space 4
.align 2
label_10_v0:
.space 4
.align 2
label_42_v16:
.space 4
.align 2
label_72_v31:
.space 4
.align 2
label_81_v36:
.space 4
.align 2
label_15_v3:
.space 4

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
# Original instruction: addiu $sp,$sp,-40
addiu $sp,$sp,-40
# Original instruction: li v0,1
li $t5,1
la $t0,label_10_v0
sw $t5,0($t0)
# Original instruction: addiu v1,$fp,-12
addiu $t5,$fp,-12
la $t0,label_11_v1
sw $t5,0($t0)
# Original instruction: sw v0,0(v1)
la $t5,label_10_v0
lw $t5,0($t5)
la $t4,label_11_v1
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v2,2
li $t5,2
la $t0,label_14_v2
sw $t5,0($t0)
# Original instruction: addiu v3,$fp,-16
addiu $t5,$fp,-16
la $t0,label_15_v3
sw $t5,0($t0)
# Original instruction: sw v2,0(v3)
la $t5,label_14_v2
lw $t5,0($t5)
la $t4,label_15_v3
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v4,3
li $t5,3
la $t0,label_18_v4
sw $t5,0($t0)
# Original instruction: addiu v5,$fp,-20
addiu $t5,$fp,-20
la $t0,label_19_v5
sw $t5,0($t0)
# Original instruction: sw v4,0(v5)
la $t5,label_18_v4
lw $t5,0($t5)
la $t4,label_19_v5
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v6,4
li $t5,4
la $t0,label_22_v6
sw $t5,0($t0)
# Original instruction: addiu v7,$fp,-24
addiu $t5,$fp,-24
la $t0,label_23_v7
sw $t5,0($t0)
# Original instruction: sw v6,0(v7)
la $t5,label_22_v6
lw $t5,0($t5)
la $t4,label_23_v7
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v8,5
li $t5,5
la $t0,label_26_v8
sw $t5,0($t0)
# Original instruction: addiu v9,$fp,-28
addiu $t5,$fp,-28
la $t0,label_27_v9
sw $t5,0($t0)
# Original instruction: sw v8,0(v9)
la $t5,label_26_v8
lw $t5,0($t5)
la $t4,label_27_v9
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v10,6
li $t5,6
la $t0,label_30_v10
sw $t5,0($t0)
# Original instruction: addiu v11,$fp,-32
addiu $t5,$fp,-32
la $t0,label_31_v11
sw $t5,0($t0)
# Original instruction: sw v10,0(v11)
la $t5,label_30_v10
lw $t5,0($t5)
la $t4,label_31_v11
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v12,0
li $t5,0
la $t0,label_34_v12
sw $t5,0($t0)
# Original instruction: addiu v13,$fp,-36
addiu $t5,$fp,-36
la $t0,label_35_v13
sw $t5,0($t0)
# Original instruction: sw v12,0(v13)
la $t5,label_34_v12
lw $t5,0($t5)
la $t4,label_35_v13
lw $t4,0($t4)
sw $t5,0($t4)
label_6_while_start:
# Original instruction: addiu v14,$fp,-36
addiu $t5,$fp,-36
la $t0,label_37_v14
sw $t5,0($t0)
# Original instruction: lw v15,0(v14)
la $t5,label_37_v14
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_39_v15
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v15,0($sp)
la $t5,label_39_v15
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: li v16,5
li $t5,5
la $t0,label_42_v16
sw $t5,0($t0)
# Original instruction: lw v17,0($sp)
lw $t5,0($sp)
la $t0,label_44_v17
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v18,v16,$zero
la $t5,label_42_v16
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_45_v18
sw $t4,0($t0)
# Original instruction: slt v19,v17,v18
la $t5,label_44_v17
lw $t5,0($t5)
la $t4,label_45_v18
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_47_v19
sw $t3,0($t0)
# Original instruction: beqz v19,label_7_while_exit
la $t5,label_47_v19
lw $t5,0($t5)
beqz $t5,label_7_while_exit
# Original instruction: addiu v20,$fp,-16
addiu $t5,$fp,-16
la $t0,label_49_v20
sw $t5,0($t0)
# Original instruction: lw v21,0(v20)
la $t5,label_49_v20
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_51_v21
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v21,0($sp)
la $t5,label_51_v21
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v22,$fp,-32
addiu $t5,$fp,-32
la $t0,label_53_v22
sw $t5,0($t0)
# Original instruction: lw v23,0(v22)
la $t5,label_53_v22
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_56_v23
sw $t4,0($t0)
# Original instruction: lw v24,0($sp)
lw $t5,0($sp)
la $t0,label_58_v24
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v25,v23,$zero
la $t5,label_56_v23
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_59_v25
sw $t4,0($t0)
# Original instruction: add v26,v24,v25
la $t5,label_58_v24
lw $t5,0($t5)
la $t4,label_59_v25
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_62_v26
sw $t3,0($t0)
# Original instruction: addiu v27,$fp,-12
addiu $t5,$fp,-12
la $t0,label_63_v27
sw $t5,0($t0)
# Original instruction: sw v26,0(v27)
la $t5,label_62_v26
lw $t5,0($t5)
la $t4,label_63_v27
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v28,$fp,-12
addiu $t5,$fp,-12
la $t0,label_65_v28
sw $t5,0($t0)
# Original instruction: lw v29,0(v28)
la $t5,label_65_v28
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_67_v29
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v29,0($sp)
la $t5,label_67_v29
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v30,$fp,-28
addiu $t5,$fp,-28
la $t0,label_69_v30
sw $t5,0($t0)
# Original instruction: lw v31,0(v30)
la $t5,label_69_v30
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_72_v31
sw $t4,0($t0)
# Original instruction: lw v32,0($sp)
lw $t5,0($sp)
la $t0,label_74_v32
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v33,v31,$zero
la $t5,label_72_v31
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_75_v33
sw $t4,0($t0)
# Original instruction: add v34,v32,v33
la $t5,label_74_v32
lw $t5,0($t5)
la $t4,label_75_v33
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_78_v34
sw $t3,0($t0)
# Original instruction: addiu v35,$fp,-16
addiu $t5,$fp,-16
la $t0,label_79_v35
sw $t5,0($t0)
# Original instruction: sw v34,0(v35)
la $t5,label_78_v34
lw $t5,0($t5)
la $t4,label_79_v35
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v36,$fp,-16
addiu $t5,$fp,-16
la $t0,label_81_v36
sw $t5,0($t0)
# Original instruction: lw v37,0(v36)
la $t5,label_81_v36
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_83_v37
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v37,0($sp)
la $t5,label_83_v37
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v38,$fp,-24
addiu $t5,$fp,-24
la $t0,label_85_v38
sw $t5,0($t0)
# Original instruction: lw v39,0(v38)
la $t5,label_85_v38
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_88_v39
sw $t4,0($t0)
# Original instruction: lw v40,0($sp)
lw $t5,0($sp)
la $t0,label_90_v40
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v41,v39,$zero
la $t5,label_88_v39
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_91_v41
sw $t4,0($t0)
# Original instruction: add v42,v40,v41
la $t5,label_90_v40
lw $t5,0($t5)
la $t4,label_91_v41
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_94_v42
sw $t3,0($t0)
# Original instruction: addiu v43,$fp,-20
addiu $t5,$fp,-20
la $t0,label_95_v43
sw $t5,0($t0)
# Original instruction: sw v42,0(v43)
la $t5,label_94_v42
lw $t5,0($t5)
la $t4,label_95_v43
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v44,$fp,-20
addiu $t5,$fp,-20
la $t0,label_97_v44
sw $t5,0($t0)
# Original instruction: lw v45,0(v44)
la $t5,label_97_v44
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_99_v45
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v45,0($sp)
la $t5,label_99_v45
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v46,$fp,-28
addiu $t5,$fp,-28
la $t0,label_101_v46
sw $t5,0($t0)
# Original instruction: lw v47,0(v46)
la $t5,label_101_v46
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_104_v47
sw $t4,0($t0)
# Original instruction: lw v48,0($sp)
lw $t5,0($sp)
la $t0,label_106_v48
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v49,v47,$zero
la $t5,label_104_v47
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_107_v49
sw $t4,0($t0)
# Original instruction: add v50,v48,v49
la $t5,label_106_v48
lw $t5,0($t5)
la $t4,label_107_v49
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_110_v50
sw $t3,0($t0)
# Original instruction: addiu v51,$fp,-24
addiu $t5,$fp,-24
la $t0,label_111_v51
sw $t5,0($t0)
# Original instruction: sw v50,0(v51)
la $t5,label_110_v50
lw $t5,0($t5)
la $t4,label_111_v51
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v52,$fp,-24
addiu $t5,$fp,-24
la $t0,label_113_v52
sw $t5,0($t0)
# Original instruction: lw v53,0(v52)
la $t5,label_113_v52
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_115_v53
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v53,0($sp)
la $t5,label_115_v53
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v54,$fp,-32
addiu $t5,$fp,-32
la $t0,label_117_v54
sw $t5,0($t0)
# Original instruction: lw v55,0(v54)
la $t5,label_117_v54
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_120_v55
sw $t4,0($t0)
# Original instruction: lw v56,0($sp)
lw $t5,0($sp)
la $t0,label_122_v56
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v57,v55,$zero
la $t5,label_120_v55
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_123_v57
sw $t4,0($t0)
# Original instruction: add v58,v56,v57
la $t5,label_122_v56
lw $t5,0($t5)
la $t4,label_123_v57
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_126_v58
sw $t3,0($t0)
# Original instruction: addiu v59,$fp,-28
addiu $t5,$fp,-28
la $t0,label_127_v59
sw $t5,0($t0)
# Original instruction: sw v58,0(v59)
la $t5,label_126_v58
lw $t5,0($t5)
la $t4,label_127_v59
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v60,$fp,-28
addiu $t5,$fp,-28
la $t0,label_129_v60
sw $t5,0($t0)
# Original instruction: lw v61,0(v60)
la $t5,label_129_v60
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_131_v61
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v61,0($sp)
la $t5,label_131_v61
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v62,$fp,-12
addiu $t5,$fp,-12
la $t0,label_133_v62
sw $t5,0($t0)
# Original instruction: lw v63,0(v62)
la $t5,label_133_v62
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_136_v63
sw $t4,0($t0)
# Original instruction: lw v64,0($sp)
lw $t5,0($sp)
la $t0,label_138_v64
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v65,v63,$zero
la $t5,label_136_v63
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_139_v65
sw $t4,0($t0)
# Original instruction: add v66,v64,v65
la $t5,label_138_v64
lw $t5,0($t5)
la $t4,label_139_v65
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_142_v66
sw $t3,0($t0)
# Original instruction: addiu v67,$fp,-32
addiu $t5,$fp,-32
la $t0,label_143_v67
sw $t5,0($t0)
# Original instruction: sw v66,0(v67)
la $t5,label_142_v66
lw $t5,0($t5)
la $t4,label_143_v67
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v68,$fp,-36
addiu $t5,$fp,-36
la $t0,label_145_v68
sw $t5,0($t0)
# Original instruction: lw v69,0(v68)
la $t5,label_145_v68
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_147_v69
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v69,0($sp)
la $t5,label_147_v69
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: li v70,1
li $t5,1
la $t0,label_150_v70
sw $t5,0($t0)
# Original instruction: lw v71,0($sp)
lw $t5,0($sp)
la $t0,label_152_v71
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v72,v70,$zero
la $t5,label_150_v70
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_153_v72
sw $t4,0($t0)
# Original instruction: add v73,v71,v72
la $t5,label_152_v71
lw $t5,0($t5)
la $t4,label_153_v72
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_156_v73
sw $t3,0($t0)
# Original instruction: addiu v74,$fp,-36
addiu $t5,$fp,-36
la $t0,label_157_v74
sw $t5,0($t0)
# Original instruction: sw v73,0(v74)
la $t5,label_156_v73
lw $t5,0($t5)
la $t4,label_157_v74
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: j label_6_while_start
j label_6_while_start
label_7_while_exit:
# Original instruction: addiu v75,$fp,-12
addiu $t5,$fp,-12
la $t0,label_159_v75
sw $t5,0($t0)
# Original instruction: lw v76,0(v75)
la $t5,label_159_v75
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_161_v76
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v76,0($sp)
la $t5,label_161_v76
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v77,$fp,-16
addiu $t5,$fp,-16
la $t0,label_163_v77
sw $t5,0($t0)
# Original instruction: lw v78,0(v77)
la $t5,label_163_v77
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_166_v78
sw $t4,0($t0)
# Original instruction: lw v79,0($sp)
lw $t5,0($sp)
la $t0,label_168_v79
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v80,v78,$zero
la $t5,label_166_v78
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_169_v80
sw $t4,0($t0)
# Original instruction: add v81,v79,v80
la $t5,label_168_v79
lw $t5,0($t5)
la $t4,label_169_v80
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_171_v81
sw $t3,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v81,0($sp)
la $t5,label_171_v81
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v82,$fp,-20
addiu $t5,$fp,-20
la $t0,label_173_v82
sw $t5,0($t0)
# Original instruction: lw v83,0(v82)
la $t5,label_173_v82
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_176_v83
sw $t4,0($t0)
# Original instruction: lw v84,0($sp)
lw $t5,0($sp)
la $t0,label_178_v84
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v85,v83,$zero
la $t5,label_176_v83
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_179_v85
sw $t4,0($t0)
# Original instruction: add v86,v84,v85
la $t5,label_178_v84
lw $t5,0($t5)
la $t4,label_179_v85
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_181_v86
sw $t3,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v86,0($sp)
la $t5,label_181_v86
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v87,$fp,-24
addiu $t5,$fp,-24
la $t0,label_183_v87
sw $t5,0($t0)
# Original instruction: lw v88,0(v87)
la $t5,label_183_v87
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_186_v88
sw $t4,0($t0)
# Original instruction: lw v89,0($sp)
lw $t5,0($sp)
la $t0,label_188_v89
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v90,v88,$zero
la $t5,label_186_v88
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_189_v90
sw $t4,0($t0)
# Original instruction: add v91,v89,v90
la $t5,label_188_v89
lw $t5,0($t5)
la $t4,label_189_v90
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_191_v91
sw $t3,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v91,0($sp)
la $t5,label_191_v91
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v92,$fp,-28
addiu $t5,$fp,-28
la $t0,label_193_v92
sw $t5,0($t0)
# Original instruction: lw v93,0(v92)
la $t5,label_193_v92
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_196_v93
sw $t4,0($t0)
# Original instruction: lw v94,0($sp)
lw $t5,0($sp)
la $t0,label_198_v94
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v95,v93,$zero
la $t5,label_196_v93
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_199_v95
sw $t4,0($t0)
# Original instruction: add v96,v94,v95
la $t5,label_198_v94
lw $t5,0($t5)
la $t4,label_199_v95
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_201_v96
sw $t3,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v96,0($sp)
la $t5,label_201_v96
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v97,$fp,-32
addiu $t5,$fp,-32
la $t0,label_203_v97
sw $t5,0($t0)
# Original instruction: lw v98,0(v97)
la $t5,label_203_v97
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_206_v98
sw $t4,0($t0)
# Original instruction: lw v99,0($sp)
lw $t5,0($sp)
la $t0,label_208_v99
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v100,v98,$zero
la $t5,label_206_v98
lw $t5,0($t5)
add $t4,$t5,$zero
la $t0,label_209_v100
sw $t4,0($t0)
# Original instruction: add v101,v99,v100
la $t5,label_208_v99
lw $t5,0($t5)
la $t4,label_209_v100
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_212_v101
sw $t3,0($t0)
# Original instruction: addiu v102,$fp,-40
addiu $t5,$fp,-40
la $t0,label_213_v102
sw $t5,0($t0)
# Original instruction: sw v101,0(v102)
la $t5,label_212_v101
lw $t5,0($t5)
la $t4,label_213_v102
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v103,$fp,-40
addiu $t5,$fp,-40
la $t0,label_215_v103
sw $t5,0($t0)
# Original instruction: lw v104,0(v103)
la $t5,label_215_v103
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_217_v104
sw $t4,0($t0)
# Original instruction: add $a0,v104,$zero
la $t5,label_217_v104
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v105,$zero,$zero
add $t5,$zero,$zero
la $t0,label_218_v105
sw $t5,0($t0)
# Original instruction: li v106,0
li $t5,0
la $t0,label_220_v106
sw $t5,0($t0)
# Original instruction: add $v0,v106,$zero
la $t5,label_220_v106
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_main
j label_5_return_main
label_5_return_main:
# Original instruction: addiu $sp,$sp,40
addiu $sp,$sp,40
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

