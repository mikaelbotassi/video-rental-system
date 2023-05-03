import AppDropdown from '../components/dropdown/dropdownComponent.vue';
import ActionDropdown from '../components/dropdown/actionDropdown.vue'
import DropdownItem from '../components/dropdown/dropdownItem.vue'
import TrashIcon from '../components/icons/trashFillComponent.vue'
import EditIcon from '../components/icons/editFillComponent.vue'
import MyTable from '../components/tables/tableList.vue'
import MyThead from '../components/tables/theadComponent.vue'
import MyRow from '../components/tables/trowComponent.vue'
import MyModal from '../components/modal/modalComponent.vue'
import MyModalBody from '../components/modal/modalBody.vue'
import MyModalFooter from '../components/modal/modalFooter.vue'
import MyModalHeader from '../components/modal/modalHeader.vue'

export default {
    install: (app:any, options:any) => {
        app.component("app-dropdown", AppDropdown)
        app.component("action-dropdown", ActionDropdown)
        app.component("dropdown-item", DropdownItem)
        app.component("trash-icon", TrashIcon)
        app.component("edit-icon", EditIcon)
        app.component("my-table", MyTable)
        app.component("my-thead", MyThead)
        app.component("my-tr", MyRow)
        app.component("my-modal", MyModal)
        app.component("modal-header", MyModalHeader)
        app.component("modal-footer", MyModalFooter)
        app.component("modal-body", MyModalBody)
    }
  }