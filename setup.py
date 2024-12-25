import os


def create_kotlin_study_structure(base_path):
    # Define the folder structure
    folders = [
        "basics/variables",
        "basics/control_flow",
        "basics/functions",
        "basics/classes",
        "basics/collections",
        "intermediate/coroutines",
        "intermediate/extensions",
        "intermediate/generics",
        "intermediate/null_safety",
        "advanced/kotlin_multiplatform",
        "advanced/dsl",
        "advanced/reflection",
        "advanced/testing",
        "android/basic_app",
        "android/mvvm_app",
        "android/compose_ui_app",
        "projects/todo_app",
        "projects/chat_app",
        "projects/api_consumer",
    ]

    # Create folders
    for folder in folders:
        os.makedirs(os.path.join(base_path, folder), exist_ok=True)

    # # Create a workspace file
    # workspace_file = os.path.join(base_path, "kotlin_study.code-workspace")
    # with open(workspace_file, "w") as f:
    #     f.write("{\n\t\"folders\": [\n\t\t{\"path\": \"./\"}\n\t]\n}")

    print(f"Kotlin study structure created at {base_path}")


# Specify the base path where the structure will be created
# base_path = os.path.expanduser("~/dev/kotlin_study")
base_path = os.path.expanduser(".")
create_kotlin_study_structure(base_path)
