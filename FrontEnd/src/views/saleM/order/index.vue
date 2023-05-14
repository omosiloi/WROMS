<template>
  <div>
    <div style="text-align: center">
      <el-transfer
        style="text-align: left; display: inline-block"
        v-model="value"
        filterable
        :left-default-checked="[2, 3]"
        :right-default-checked="[1]"
        :render-content="renderFunc"
        :titles="['菜单', '']"
        :button-texts="['到左边', '到右边']"
        :format="{
          noChecked: '${total}',
          hasChecked: '${checked}/${total}'
        }"
        @change="handleChange"
        :data="data">
        <el-button class="transfer-footer" slot="left-footer" size="small">清空选项</el-button>
        <el-button class="transfer-footer" slot="right-footer" size="small">清空选项</el-button>
      </el-transfer>
    </div>
  </div>
</template>

<script>
// import { list } from '@/api/saleManagement'
export default {
  name: 'OrderDish',
  data() {
      const generateData = _ => {
        const data = []
        for (let i = 1; i <= 15; i++) {
          data.push({
            key: i,
            label: `备选项 ${i}`,
            disabled: i % 4 === 0
          })
        }
        return data
      }
      return {
        data: generateData(),
        value: [1],
        value4: [1],
        renderFunc(h, option) {
          return <span>{ option.key } - { option.label }</span>
        }
      }
    },

    methods: {
      handleChange(value, direction, movedKeys) {
        console.log(value, direction, movedKeys)
      }
    }
}
</script>

<style>
.transfer-footer {
    margin-left: 20px;
    padding: 6px 5px;
  }
</style>