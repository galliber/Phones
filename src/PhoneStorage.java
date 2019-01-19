public class PhoneStorage {
    private class Node{
        private int value;
        private Node prev;
        private Node[] nodes=new Node[10];

        Node(){
            this.prev=null;
        }

        Node(int value){
            this.value=value;
            this.prev=null;
        }

        Node(int value, Node prev){
            this.value=value;
            this.prev=prev;
        }

        public Node[] getNodes(){
            return nodes;
        }

        public void setNodes(int index, Node node){
            nodes[index]=node;
        }

        public void deleteNode(int index){
            nodes[index]=null;
        }

        void setPrev(Node prev){
            this.prev=prev;
        }

        Node getPrev(){
            return this.prev;
        }
    }
    private boolean found;
    private Node[] nodes=new Node[10];

    void addPhone(String phone) {
        if(nodes[(int) (phone.charAt(0)-'0')] == null){
            nodes[(int) (phone.charAt(0)-'0')]=new Node((int) (phone.charAt(0)-'0'));
            Node node=nodes[(int) (phone.charAt(0)-'0')];
            add(1, phone, node);
        }
        else
            add(1, phone, nodes[(int) (phone.charAt(0)-'0')]);
    }

    boolean findPhone(String phone){
        found=false;
        if(nodes[(int)(phone.charAt(0)-'0')]==null)
            return false;
        else {
            find(1, phone, nodes[(int) (phone.charAt(0)-'0')]);
        }
        return found;
    }

    boolean deletePhone(String phone){
        if(findPhone(phone)){

            return true;
        }
        return false;
    }

    void add(int index, String phone, Node node){
        if(index >9)
            return;
        Node next=node.getNodes()[(int) (phone.charAt(index)-'0')];
        if(next==null){
            next=new Node((int) (phone.charAt(index)-'0'), node);
            node.getNodes()[(int) (phone.charAt(index)-'0')]=next;
            add(++index, phone, next);
        }
        else {
            add(++index, phone, next);
        }

    }

    void find(int index, String phone, Node node){
        if(index>9) {
            found = true;
            return;
        }
        Node next=node.getNodes()[(int) (phone.charAt(index)-'0')];
        if(index<9) {
            if (next == null)
                return;
            else
                find(++index, phone, next);
        }
        else{
            found=true;
            return;
        }
    }

}
